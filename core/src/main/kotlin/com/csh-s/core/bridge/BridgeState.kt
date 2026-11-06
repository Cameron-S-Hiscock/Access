package com.csh_s.core.bridge

enum class BridgeState {
    PENDING,
    OPEN,
    CLOSED,
    PAUSED,
    CIRCULAR_DEPENDENCY,
}