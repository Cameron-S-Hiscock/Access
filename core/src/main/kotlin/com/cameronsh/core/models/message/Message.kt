package com.cameronsh.core.models.message

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.models.task.Task

data class Message(
    val name: String = "Message",
    val task: Task,
    val destination: String
) {
    public val id: UUID = Id.genId()
    val origin: UUID = task.id
}
