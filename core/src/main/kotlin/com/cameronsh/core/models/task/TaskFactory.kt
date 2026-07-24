package com.cameronsh.core.models.task

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.models.task.*
import com.cameronsh.core.registry.RegistryService

object TaskFactory {
    public val id: UUID = Id.genId()
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
