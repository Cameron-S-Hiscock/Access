package com.cameronsh.utils

import java.util.UUID

object Id {
    val ids: MutableMap<UUID, Any> = mutableMapOf()
    fun genId(obj: Any) {
        val id = java.util.UUID.randomUUID()
        ids[id] = obj
    }
}
