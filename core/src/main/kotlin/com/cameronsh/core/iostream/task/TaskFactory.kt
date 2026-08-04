package com.cameronsh.core.iostream.task

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.*
import com.cameronsh.core.registry.RegistryService

object TaskFactory {
    val id: UUID = Id.genId(this)
    fun create(
        name: String,
        priority: TaskPriority,
        action: () -> Unit
    ): Task {
        val task = Task(
            name = name,
            priority = priority,
            action = action
        )
        println("Created task: ${name}Task")
        RegistryService.registerTask(task)
        return task
    }
}
