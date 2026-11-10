package com.csh_s.core.iostream.message

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.data.Data
import com.csh_s.core.iostream.message.MessagePriority.*
import com.csh_s.core.iostream.message.MessageState
import com.csh_s.core.iostream.message.MessageError

data class Message(
    val name: String = "Message",
    val priority: MessagePriority = NORMAL,
    val origin: UUID,
    val destination: UUID,
    val task: Task? = null,
    val data: Data? = null,
) {
    val id: UUID = Id.genId(this)
    var state: MessageState = MessageState.PENDING
}
