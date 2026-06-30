package net.accesstechnologies.core.models.tasks

import net.accesstechnologies.core.models.tasks.*
import net.accesstechnologies.core.registry.RegistryService

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
