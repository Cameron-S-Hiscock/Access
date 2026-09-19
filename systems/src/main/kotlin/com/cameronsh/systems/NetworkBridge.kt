package com.cameronsh.systems

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.systems.SystemsBridge

object NetworkBridge {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("NetworkBridge", id) }
}