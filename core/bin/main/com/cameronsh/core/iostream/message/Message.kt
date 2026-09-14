package com.cameronsh.core.iostream.message

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.data.Data
import com.cameronsh.core.iostream.message.MessagePriority.*
import com.cameronsh.core.iostream.message.MessageState.*

data class Message(
    val name: String = "Message",
    val priority: MessagePriority = NORMAL,
    val origin: UUID,
    val destination: UUID,
    val task: Task? = null,
    val data: Data? = null,
) {
    val id: UUID = Id.genId(this)
    var state: MessageState = PENDING
}
