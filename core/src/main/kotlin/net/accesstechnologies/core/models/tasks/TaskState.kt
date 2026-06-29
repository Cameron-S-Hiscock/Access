package net.accesstechnologies.core.models.tasks

enum class TaskState {
    PENDING,
    SCHEDULED,
    RUNNING,
    COMPLETED,
    FAILED,
    CANCELLED,
    PAUSED
}