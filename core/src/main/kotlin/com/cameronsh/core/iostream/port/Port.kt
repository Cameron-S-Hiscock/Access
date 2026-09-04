package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.pipeline.Pipeline
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
    val messageCache = LinkedBlockingDeque<Message>()

    fun send(target: UUID?, message: Message) {
        if(targets.containsKey(target) && target != null) {
            targets[target]!!.deliver(message)
        } else {
            // TODO: Add handling for this case
            println("Target not in targets")
        }
    }
    
    fun receive(): Message? {
        return messageCache.pollFirst()
    }
}
