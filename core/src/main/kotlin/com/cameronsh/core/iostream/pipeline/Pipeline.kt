package com.cameronsh.core.iostream.pipeline

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.port.PortState
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.message.MessageState
import com.cameronsh.core.iostream.task.TaskFactory

class Pipeline(
    val name: String = "Pipeline",
    val origin: Port,
    val destination: Port,
) {
    val id: UUID = Id.genId(this)
    private val processWorker = ProcessWorker(
        name = "${name}ProcessWorker",
        host = id,
    )
    init { processWorker.start() }

    suspend fun deliver(message: Message?): Result<Unit> {
        if(message == null) {
            return Result.failure(IllegalArgumentException(PipelineError.InvalidMessageState(null).toString()))
        }
        if(message.state != MessageState.SENDING) {
            return Result.failure(IllegalArgumentException(PipelineError.InvalidMessageState(message.state).toString()))
        }
        if(destination.state != PortState.OPEN) {
            return Result.failure(IllegalArgumentException(PipelineError.InvalidDestination(destination.id).toString()))
        }
        
        destination.cache.offerLast(message)
        message.state = MessageState.SENT
        return Result.success(Unit)
    }
}
