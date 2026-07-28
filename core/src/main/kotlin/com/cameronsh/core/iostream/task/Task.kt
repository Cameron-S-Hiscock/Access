package com.cameronsh.core.iostream.task

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.data.*

data class Task (
    val name: String = "Task",
    var state: TaskState = TaskState.PENDING,
    val priority: TaskPriority = TaskPriority.NORMAL,
    val action: () -> Unit
) {
    public val id: UUID = Id.genId()
    val data: Data = DataFactory.create(input = id)
    val factory: UUID = TaskFactory.id
}
