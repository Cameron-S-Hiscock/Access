package com.csh_s.core.iostream.task

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.iostream.data.Data
import com.csh_s.core.iostream.data.DataFactory

data class Task (
    val name: String = "Task",
    var state: TaskState = TaskState.PENDING,
    val priority: TaskPriority = TaskPriority.NORMAL,
    private val dataFactory: DataFactory,
    val action: suspend () -> Any?
) {
    val id: UUID = Id.genId(this)
    val data = dataFactory.create(input = this)
}
