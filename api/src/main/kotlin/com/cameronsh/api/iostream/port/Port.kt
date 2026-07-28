package com.cameronsh.api.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.models.message
import com.cameronsh.api.iostream.port.PortStatus

interface Port {
    val id: UUID = Id.genId()
    val status: PortStatus = PortStatus.OPEN

    fun send(message: Message)
    fun recieve(): Message
}
