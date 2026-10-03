package com.cameronsh.core.iostream

abstract class IOSuccess() {
    data class Value(val value: Any? = null) : IOSuccess()
}