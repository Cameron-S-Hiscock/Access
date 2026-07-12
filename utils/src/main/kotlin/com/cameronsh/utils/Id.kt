package com.cameronsh.utils

import java.util.UUID

object Id {
    fun genId(): String = java.util.UUID.randomUUID().toString()
}
