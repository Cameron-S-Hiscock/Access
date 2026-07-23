package com.cameronsh.core.models.task

import com.cameronsh.utils.Id

import com.cameronsh.core.models.task.*
import com.cameronsh.core.registry.RegistryService

object TaskFactory {
    val id: String = Id.genId()
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
