package com.cameronsh.core.iostream.task

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.data.Data
import com.cameronsh.core.iostream.data.DataFactory

data class Task (
    val name: String = "Task",
    var state: TaskState = TaskState.PENDING,
    val priority: TaskPriority = TaskPriority.NORMAL,
    private val dataFactory: DataFactory,
    val action: () -> Unit
) {
    val id: UUID = Id.genId(this)
    val data = dataFactory.create(input = this)
}
