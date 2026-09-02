package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.IOStream
import com.cameronsh.core.iostream.message.MessageFactory
import com.cameronsh.core.BridgeRepository
import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.core.iostream.IOStreamAuthorTable

object UICoreBridge {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("UICoreBridge", id) }
    private val processWorker = ProcessWorker(
        name = "UICoreBridgeProcessWorker",
        host = id,
    )
    init { processWorker.start() }
    val IO = IOStream(
        name = "UICoreBridgeIOStream",
        targets = arrayOf(Id.objectIds["Composer"], Id.objectIds["Controller"]),
    )

    val messageFactory = MessageFactory()

    val UIInCache = LinkedBlockingDeque<Message>()
    val UIOutCache = LinkedBlockingDeque<Message>()
    val CoreInCache = LinkedBlockingDeque<Message>()
    val CoreOutCache = LinkedBlockingDeque<Message>()

    init {
        BridgeRepository.iostreams.putIfAbsent("UICoreBridge", IO)
        IOStreamAuthorTable.addPair(Composer.id, Controller.id)
    }
    
    init {
        processWorker.addWork(
            processWorker.taskFactory.create(
                name = "ReceiveMessages",
            ) {
                while(true) {
                    val uiMessage = IO.receive(Id.objectIds["Composer"])
                    val coreMessage = IO.receive(Id.objectIds["Controller"])
                    if(uiMessage != null) {
                        UIOutCache.offerLast(uiMessage)
                    }
                    if(coreMessage != null) {
                        CoreOutCache.offerLast(coreMessage)
                    }
                }
            }
        )

        processWorker.addWork(
            processWorker.taskFactory.create(
                name = "SendMessages",
            ) {
                while(true) {
                    val uiMessage = UIInCache.pollFirst()
                    val coreMessage = CoreInCache.pollFirst()
                    if(uiMessage != null) {
                        IO.send(author = Controller.id, message = uiMessage)
                    }
                    if(coreMessage != null) {
                        IO.send(author = Composer.id, message = coreMessage)
                    }
                }
            }
        )
    }
}
