package com.cameronsh.core.bridge

enum class BridgeState {
    PENDING,
    OPEN,
    CLOSED,
    PAUSED,
    CIRCULAR_DEPENDENCY,
}