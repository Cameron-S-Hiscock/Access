package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.port.PortStatus.*
import com.cameronsh.core.iostream.IOStream
import com.cameronsh.core.iostream.message.Message

class StdioPort(
    hostId: UUID,
    iostreamId: UUID,
    toDestination: Boolean,
): Port {
    override val id: UUID = Id.genId(this)
    override val status = OPEN
    override val hostId = hostId
    override val host = Id.objOf(hostId)
    override val iostreamId = iostreamId
    override val iostream = Id.objOf(iostreamId) as IOStream
    override val toDestination = toDestination
    override fun send(message: Message) {
        if(toDestination) {
            iostream.destinationMessages.add(message)
        } else {
            iostream.originMessages.add(message)
        }
    }
    override fun receive(): Message? {
        if(toDestination) {
            return iostream.destinationMessages.firstOrNull()
        } else {
            return iostream.originMessages.firstOrNull()
        }
    }
}
