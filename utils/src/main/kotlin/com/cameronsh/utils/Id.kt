package com.cameronsh.utils

import java.util.UUID
import com.google.common.collect.Maps
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap

object Id {
    val ids: BiMap<UUID, Any> = Maps.synchronizedBiMap(HashBiMap.create<UUID, Any>())
    fun objOf(id: UUID): Any? = ids[id]
    fun idOf(obj: Any): UUID? = ids.inverse()[obj]

    fun genId(obj: Any): UUID {
        idOf(obj)?.let { return it }
        val id = java.util.UUID.randomUUID()
        ids[id] = obj
        return id
    }
}
