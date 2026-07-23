package com.cameronsh.core.models.message

import com.cameronsh.utils.Id

import com.cameronsh.core.models.task.Task

data class Message(
    val name: String = "Message",
    val task: Task
) {
    val id: String = Id.genId()
}
