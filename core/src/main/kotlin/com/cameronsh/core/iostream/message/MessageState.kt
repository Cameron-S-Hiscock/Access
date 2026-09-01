package com.cameronsh.core.iostream.message

enum class MessageState {
    PENDING,
    REGISTERED,
    SCHEDULED,
    QUEUED,
    SENDING,
    SENT,
    RECEIVED,
    FAILED,
    CANCELLED,
    PAUSED
}
