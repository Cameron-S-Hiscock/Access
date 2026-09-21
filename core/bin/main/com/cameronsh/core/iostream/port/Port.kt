package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.message.MessageState
import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.port.PortState
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class Port(
    val name: String = "Port",
    val host: UUID,
) {
    val id: UUID = Id.genId(this)
    private val processWorker = ProcessWorker(
        name = "${name}ProcessWorker",
        host = id,
    )
    init { processWorker.start() }

    var state: PortState = PortState.PENDING
    val targets = ConcurrentHashMap<UUID, Pipeline>()
    val cache = LinkedBlockingDeque<Message>()

    suspend fun send(target: UUID?, message: Message) {
        try {
            require(targets.containsKey(target))
            require(target != null)
            require(message.state == MessageState.SCHEDULED)
        } catch(e: Exception) {
            message.state = MessageState.FAILED
            println("Error sending ${message.name}: ${e}")
            return
        }
        message.state = MessageState.QUEUED
        targets[target]!!.deliver(message)
    }
    
    suspend fun receive(): Message? {
        val message = cache.pollFirst()
        if(message.state == MessageState.SENT) {
            message.state = MessageState.RECEIVED
            return message
        } else {
            return null
        }
    }
}
