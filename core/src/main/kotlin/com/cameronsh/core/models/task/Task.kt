package com.cameronsh.core.models.task

import com.cameronsh.utils.Id

import com.cameronsh.core.models.data.*

data class Task (
    val name: String = "Task",
    var state: TaskState = TaskState.PENDING,
    val priority: TaskPriority = TaskPriority.NORMAL,
    val action: () -> Unit
) {
    val id: String = Id.genId()
    val data: Data = DataFactory.create()
}
