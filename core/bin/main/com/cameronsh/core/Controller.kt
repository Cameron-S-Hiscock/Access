package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.bridge.BridgeRepository
import com.cameronsh.core.iostream.message.Message
import kotlinx.coroutines.*
import com.cameronsh.systems.NetworkBridge

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
    private val CoreNetworkBridge = NetworkBridge.newServer("[::1]")
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    suspend fun processMessages() {
        val bridge = BridgeRepository.bridges["UICoreBridge"]
        require(bridge != null)
        while(true) {
            val message = CoreMessageCache.pollFirst()
            if(message != null) {
                message.task?.action()
                println("Processed message: ${message.name}")
            }
            delay(10)
        }
    }

    suspend fun receiveMessages() {
        val bridge = BridgeRepository.bridges["UICoreBridge"]
        require(bridge != null)
        while(true) {
            val message = bridge.receive(id)
            if(message != null) {
                CoreMessageCache.putLast(message)
                println("Received message: ${message.name}")
            }
            delay(10)
        }
    }

    suspend fun init() {
        serviceScope.launch {
            CoreProcessWorker.run()
        }

        serviceScope.launch {
            receiveMessages()
        }
        
        serviceScope.launch {
            processMessages()
        }
    }
}
