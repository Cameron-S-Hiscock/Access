package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.google.common.collect.Maps
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import java.util.concurrent.ConcurrentHashMap

object IOStreamAuthorTable {
    val id: UUID = Id.genId(this)

    val pairs = Maps.synchronizedBiMap(HashBiMap.create<UUID, UUID>())

    fun addPair(author: UUID, target: UUID) {
        if(author in pairs || target in pairs) {
            return Unit
        }
        pairs[author] = target
    }
}
