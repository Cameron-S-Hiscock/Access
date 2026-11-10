package com.csh_s.libaccess

import com.csh_s.utils.Id
import java.util.UUID

import java.io.File
import java.lang.foreign.*
import java.lang.invoke.MethodHandle

object LibAccessBridge {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("LibAccessBridge", id) }
}
