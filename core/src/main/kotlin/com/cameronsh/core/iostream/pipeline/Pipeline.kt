package com.cameronsh.core.iostream.pipeline

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.ProcessWorker
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

    fun deliver() {
        val message = origin.outgoingCache.pollFirst()
        if(message != null) {
            destination.incomingCache.offerLast(message)
        }
    }

    init {
        while(true) {
            processWorker.addWork(processWorker.taskFactory.create(name = "${processWorker.name}AddDelivery") { deliver() })
        }
    }
}
