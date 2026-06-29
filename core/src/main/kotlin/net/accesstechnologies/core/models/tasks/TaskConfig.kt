package net.accesstechnologies.core.models.tasks

import net.accesstechnologies.core.models.tasks.TaskPriority

data class TaskConfig(
    val priority: TaskPriority = TaskPriority.NORMAL,
    val timeout: Long? = null,
    val retryCount: Int = 0,
    val retryDelay: Long = 1000L
)