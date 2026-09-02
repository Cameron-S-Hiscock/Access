package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.ConcurrentHashMap
import com.cameronsh.core.iostream.IOStream

object BridgeRepository {
    val id: UUID = Id.genId(this)
    
    val iostreams = ConcurrentHashMap<String, IOStream>()
}
