package com.cameronsh.core.iostream.message

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.Task

data class Message(
    val name: String = "Message",
    val task: Task,
    val destination: String
) {
    val id: UUID = Id.genId()
    val origin: UUID = task.id
}
