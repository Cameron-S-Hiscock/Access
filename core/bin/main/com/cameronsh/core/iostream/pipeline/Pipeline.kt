package com.csh_s.core.iostream.pipeline

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.iostream.port.Port
import com.csh_s.core.iostream.port.PortState
import com.csh_s.core.ProcessWorker
import com.csh_s.core.iostream.message.Message
import com.csh_s.core.iostream.message.MessageState
import com.csh_s.core.iostream.task.TaskFactory
import com.csh_s.core.iostream.IOResult
import com.csh_s.core.iostream.IOSuccess
import com.csh_s.core.iostream.IOError
import com.csh_s.utils.AppSuccess

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
        return IOResult(success = AppSuccess.Value(message))
    }
}
