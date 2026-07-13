package com.cameronsh.core.models.tasks

import com.cameronsh.utils.Id

data class Task (
    val name: String,
    var state: TaskState = TaskState.PENDING,
    val priority: TaskPriority = TaskPriority.NORMAL,
    val action: () -> Unit
) {
    val id: String = Id.genId()
}
