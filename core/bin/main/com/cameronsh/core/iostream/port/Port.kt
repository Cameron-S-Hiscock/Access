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
import kotlinx.coroutines.*

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

    suspend fun init() = runBlocking {
        state = PortState.OPEN
    }

    suspend fun send(target: UUID?, message: Message): Result<Unit> {
        if(target == null) {
            return Result.failure(IllegalArgumentException(PortError.InvalidTarget(null).toString()))
        }
        if(!targets.containsKey(target)) {
            return Result.failure(IllegalArgumentException(PortError.InvalidTarget(target).toString()))
        }
        if(message.state != MessageState.SCHEDULED) {
            return Result.failure(IllegalArgumentException(PortError.InvalidMessageState(message.state).toString()))
        }
        message.state = MessageState.QUEUED
        message.state = MessageState.SENDING
        targets[target]!!.deliver(message)
        return Result.success(Unit)
    }
    
    suspend fun receive(): Message? {
        val message = cache.pollFirst()
        if(message == null) {
            return null
        }
        if(message.state == MessageState.SENT) {
            message.state = MessageState.RECEIVED
            return message
        } else {
            return null
        }
    }
}
