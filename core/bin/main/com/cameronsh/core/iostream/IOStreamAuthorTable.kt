package com.csh_s.core.iostream

import com.csh_s.utils.Id
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
        pairs[target] = author
    }
}
