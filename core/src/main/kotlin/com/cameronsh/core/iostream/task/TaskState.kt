package com.cameronsh.core.iostream.task

enum class TaskState {
    PENDING,
    SCHEDULED,
    RUNNING,
    COMPLETED,
    FAILED,
    CANCELLED,
    PAUSED
}
