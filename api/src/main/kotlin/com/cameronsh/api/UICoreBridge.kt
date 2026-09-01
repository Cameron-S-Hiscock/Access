package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.IOStream

object UICoreBridge {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("UICoreBridge", id) }
    private val processWorker = ProcessWorker(
        name = "UICoreBridgeProcessWorker",
        host = id,
    )
    init { processWorker.start() }
    val io = IOStream(
        name = "UICoreBridgeIOStream",
        targets = arrayOf(Id.objectIds["Composer"], Id.objectIds["Controller"]),
    )

    val UIInCache = LinkedBlockingDeque<Message>()
    val UIOutCache = LinkedBlockingDeque<Message>()
    val CoreInCache = LinkedBlockingDeque<Message>()
    val CoreOutCache = LinkedBlockingDeque<Message>()
    
    init {
        processWorker.addWork(
            processWorker.taskFactory.create(
                name = "ReceiveMessages",
            ) {
                while(true) {
                    val uiMessage = io.receive(Id.objectIds["Composer"])
                    val coreMessage = io.receive(Id.objectIds["Controller"])
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
                        io.send(Id.objectIds["Controller"], uiMessage)
                    }
                    if(coreMessage != null) {
                        io.send(Id.objectIds["Composer"], coreMessage)
                    }
                }
            }
        )
    }
}
