package com.cameronsh.core.iostream.pipeline

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.port.PortState
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.message.MessageState
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.iostream.IOResult
import com.cameronsh.core.iostream.IOSuccess
import com.cameronsh.core.iostream.IOError

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

    suspend fun deliver(message: Message?): IOResult {
        if(message == null) {
            return IOResult(error = PipelineError.InvalidMessageState(null))
        }
        if(message.state != MessageState.SENDING) {
            return IOResult(error = PipelineError.InvalidMessageState(message.state))
        }
        if(destination.state != PortState.OPEN) {
            return IOResult(error = PipelineError.InvalidDestination(destination.id))
        }
        
        destination.cache.offerLast(message)
        message.state = MessageState.SENT
        return IOResult(success = IOSuccess.Value(message))
    }
}
