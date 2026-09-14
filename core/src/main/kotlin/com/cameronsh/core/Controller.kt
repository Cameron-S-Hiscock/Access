package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.BridgeRepository
import com.cameronsh.core.iostream.message.Message
import kotlinx.coroutines.*

object Controller {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("Controller", id) }

    private val CoreProcessWorker = ProcessWorker(
        name = "CoreProcessWorker",
        host = id,
    )
    private val CoreMessageWorker = ProcessWorker(
        name = "CoreMessageWorker",
        host = id,
    )
    private val CoreMessageCache = LinkedBlockingDeque<Message>()

    suspend fun processMessages() {
        val IO = BridgeRepository.iostreams["UICoreBridge"]
        require(IO != null)
        while(true) {
            val message = CoreMessageCache.pollFirst()
            if(message != null) {
                CoreMessageWorker.taskFactory.create(name = "CoreProcess${message.name}") {
                    message.task?.action
                }
            }
        }
    }

    suspend fun receiveMessages() {
        val IO = BridgeRepository.iostreams["UICoreBridge"]
        require(IO != null)
        while(true) {
            val message = IO.receive(author = id)
            if(message != null) {
                CoreMessageCache.putLast(message)
            }
        }
    }

    suspend fun init() {
        withContext(Dispatchers.IO) {

        this.launch {
            CoreProcessWorker.run()
        }

        this.launch {
            receiveMessages()
        }
        
        this.launch {
            processMessages()
        }

        this.launch {
            CoreProcessWorker.submitWork(
                CoreProcessWorker.taskFactory.create(name = "UICoreBridgeCoreTest") {
                    val IO = BridgeRepository.iostreams["UICoreBridge"]
                    require(IO != null)
                    IO.send(
                        author = id,
                        message = CoreProcessWorker.messageFactory.create(
                            name = "UICoreBridgeUITestMessage",
                            origin = id,
                            destination = IO.id,
                            task = CoreProcessWorker.taskFactory.create(name = "UICoreBridgeCoreTestPrint") { println("UICoreBridgeUITestArrived") },
                        )
                    )
                }
            )
        }
        }
    }
}
