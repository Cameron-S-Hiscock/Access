package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.message.Message
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
    val targets = ConcurrentHashMap<UUID, Port>()
    val messageCache = LinkedBlockingDeque<Message>()

    fun send(target: UUID, message: Message) {
        if(targets.containsKey(target)) {
            targets[target]!!.messageCache.putFirst(message)
        } else {
            // TODO: Add handling for this case
            println("Target not in targets")
        }
    }
    
    fun receive(target: UUID): Message? {
        if(targets.containsKey(target)) {
            return targets[target]!!.messageCache.pollFirst()
        }
        return null
    }
}
