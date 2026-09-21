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

    suspend fun deliver(message: Message?) {
        try {
            require(message?.state == MessageState.QUEUED)
            require(destination.state == PortState.OPEN)
        } catch(e: Exception) {
            message?.state = MessageState.FAILED
            println("Delivery failed for ${message?.name} at ${name}: ${e}")
            return
        }
        message.state = MessageState.SENDING
        destination.cache.offerLast(message)
        message.state = MessageState.SENT
    }
}
