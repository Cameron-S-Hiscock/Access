package com.cameronsh.core.bridge

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.ConcurrentHashMap
import com.cameronsh.core.iostream.IOStream
import com.cameronsh.core.bridge.Bridge

object BridgeRepository {
    val id: UUID = Id.genId(this)
    
    val bridges = ConcurrentHashMap<String, Bridge>()
}