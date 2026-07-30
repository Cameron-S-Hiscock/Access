package com.cameronsh.core.iostream.task

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.*
import com.cameronsh.core.registry.RegistryService

object TaskFactory {
    init { Id.genId(this) }
    fun create(
        name: String,
        priority: TaskPriority,
        register: Boolean = true,
        action: () -> Unit
    ): Task {
        val task = Task(
            name = name,
            priority = priority,
            action = action
        )
        if(register) {
            RegistryService.registerTask(task)
        }
        return task
    }
}
