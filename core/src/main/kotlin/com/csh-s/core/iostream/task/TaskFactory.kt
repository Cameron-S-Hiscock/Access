package com.csh_s.core.iostream.task

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.iostream.data.DataFactory
import com.csh_s.core.iostream.task.TaskPriority.*

class TaskFactory() {
    private val dataFactory = DataFactory()

    fun create(
        name: String = "Task",
        priority: TaskPriority = NORMAL,
        action: suspend () -> Any?
    ): Task {
        val task = Task(
            name = name,
            priority = priority,
            dataFactory = dataFactory,
            action = action
        )
        return task
    }
}
