package net.accesstechnologies.core.models.tasks

import net.accesstechnologies.core.models.tasks.*
import net.accesstechnologies.core.registry.RegistryService

object TaskFactory {
    fun create(
        name: String,
        priority: TaskPriority = TaskPriority.NORMAL,
        timeout: Long? = null,
        retryCount: Int = 0,
        retryDelay: Long = 1000L,
        action: () -> Unit
    ): Task = Task(
        name = name,
        config = TaskConfig(
            priority = priority,
            timeout = timeout,
            retryCount = retryCount,
            retryDelay = retryDelay
        ),
        action = action
    )
}