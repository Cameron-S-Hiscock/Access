package com.cameronsh.utils

import java.util.UUID

object Id {
    fun genId(): UUID = java.util.UUID.randomUUID()
}
