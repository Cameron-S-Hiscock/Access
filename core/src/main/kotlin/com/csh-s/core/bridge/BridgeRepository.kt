package com.csh_s.core.bridge

import com.csh_s.utils.Id
import java.util.UUID

import java.util.concurrent.ConcurrentHashMap
import com.csh_s.core.iostream.IOStream
import com.csh_s.core.bridge.Bridge

object BridgeRepository {
    val id: UUID = Id.genId(this)
    
    val bridges = ConcurrentHashMap<String, Bridge>()
}