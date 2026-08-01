package com.cameronsh.core.iostream.port

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.message.*
import com.cameronsh.core.iostream.port.PortStatus
import com.cameronsh.core.iostream.IOStream

interface Port {
    val id: UUID
    val status: PortStatus
    val hostId: UUID
    val host: Any?
    val iostreamId: UUID
    val iostream: IOStream
    val toDestination: Boolean
    fun send(message: Message) { println("Sending $message") }
    fun receive(): Message? { println("Receiving"); return null }
}
