package com.cameronsh.core.models.tasks

import com.cameronsh.utils.Id

import com.cameronsh.core.models.tasks.*
import com.cameronsh.core.registry.RegistryService

object TaskFactory {
    val id: String = Id.genId()
    fun create(
        name: String = "task",
        priority: TaskPriority = TaskPriority.NORMAL,
        action: () -> Unit
    ): Task = Task(
        name = name,
        priority = priority,
        action = action
    )
}
