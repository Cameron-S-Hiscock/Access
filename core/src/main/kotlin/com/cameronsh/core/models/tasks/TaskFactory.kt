package com.cameronsh.core.models.tasks

import com.cameronsh.core.models.tasks.*
import com.cameronsh.core.registry.RegistryService

object TaskFactory {
    fun create(
        name: String,
        priority: TaskPriority = TaskPriority.NORMAL,
        action: () -> Unit
    ): Task = Task(
        name = name,
        priority = priority,
        action = action
    )
}
