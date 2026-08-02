package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.port.PortType.*
import com.cameronsh.core.iostream.port.*

object PortFactory {
    val id: UUID = Id.genId(this)
    fun create(
        hostId: UUID,
        iostreamId: UUID,
        type: PortType,
        toDestination: Boolean,
    ): Port {
        val port = when(type) {
            STDIO -> StdioPort(
                hostId = hostId,
                iostreamId = iostreamId,
                toDestination = toDestination,
            )
            else -> StdioPort(
                hostId = hostId,
                iostreamId = iostreamId,
                toDestination = toDestination,
            )
        }
        return port
    }
}
