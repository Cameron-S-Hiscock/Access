package com.cameronsh.core.iostream.task

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.data.DataFactory
import com.cameronsh.core.iostream.task.TaskPriority.*

class TaskFactory(
) {
    private val dataFactory = DataFactory()

    fun create(
        name: String = "Task",
        priority: TaskPriority = NORMAL,
        action: () -> Unit
    ): Task {
        val task = Task(
            name = name,
            priority = priority,
            dataFactory = dataFactory,
            action = action
        )
        println("Created task: ${name}Task")
        return task
    }
}
