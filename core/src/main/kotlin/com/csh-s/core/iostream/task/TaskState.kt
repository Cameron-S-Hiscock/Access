package com.csh_s.core.iostream.task

enum class TaskState {
    PENDING,
    REGISTERED,
    SCHEDULED,
    RUNNING,
    COMPLETED,
    FAILED,
    CANCELLED,
    PAUSED,
}
