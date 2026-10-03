package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.message.MessageError
import com.cameronsh.core.iostream.message.MessageState
import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.pipeline.PipelineError
import com.cameronsh.core.iostream.port.PortState
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlinx.coroutines.*
import com.cameronsh.core.iostream.IOResult
import com.cameronsh.core.iostream.IOSuccess
import com.cameronsh.core.iostream.IOError

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

    suspend fun send(target: UUID?, message: Message): IOResult {
        if(target == null) {
            return IOResult(error = PortError.InvalidTarget(null))
        }
        if(!targets.containsKey(target)) {
            return IOResult(error = PortError.InvalidTarget(target))
        }
        if(message.state != MessageState.SCHEDULED) {
            return IOResult(error = PortError.InvalidMessageState(message.state))
        }
        message.state = MessageState.QUEUED
        message.state = MessageState.SENDING
        val result = targets[target]!!.deliver(message)
        return result
    }
    
    suspend fun receive(): IOResult {
        val message = cache.pollFirst()
        if(message == null) {
            return IOResult(error = MessageError.NullMessage(null))
        }
        if(message.state == MessageState.SENT) {
            message.state = MessageState.RECEIVED
            return IOResult(success = IOSuccess.Value(message))
        } else {
            return IOResult(error = IOError.UnknownError(id))
        }
    }
}
