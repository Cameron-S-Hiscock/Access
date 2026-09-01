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

    val targets = ConcurrentHashMap<UUID, Port>()
    val messageCache = LinkedBlockingDeque<Message>()

    fun send(message: Message) {

    }
    
    fun receive(): Message? {
        return null
    }
}
