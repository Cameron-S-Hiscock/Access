package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.message.*
import com.cameronsh.core.iostream.port.PortStatus

interface Port {
    val id: UUID
    val status: PortStatus
}
