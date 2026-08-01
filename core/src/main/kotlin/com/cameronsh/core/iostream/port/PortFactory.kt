package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

object PortFactory {
    val id: UUID = Id.genId(this)
    fun create(
        hostId: UUID
    ) {
        val host = Id.objOf(hostId)
    }
}
