package com.csh_s.core.iostream.message

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.iostream.message.MessagePriority.*
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.data.Data

class MessageFactory() {
    val id: UUID = Id.genId(this)

    fun create(
        name: String = "Message",
        priority: MessagePriority = NORMAL,
        origin: UUID = id,
        destination: UUID,
        task: Task? = null,
        data: Data? = null,
    ): Message {
        val message = Message(
            name = name,
            priority = priority,
            origin = origin,
            destination = destination,
            task = task,
            data = data,
        )
        var state = MessageState.PENDING
        return message
    }
}
