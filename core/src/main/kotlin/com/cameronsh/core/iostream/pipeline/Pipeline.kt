package com.cameronsh.core.iostream.pipeline

import com.cameronsh.utils.Id
import java.util.UUID
import com.google.common.collect.BiMap

import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.port.StdioPort
import com.cameronsh.core.iostream.IOStream

class Pipeline(
    origin: StdioPort,
    destination: StdioPort,
) {
    val id: UUID = Id.genId(this)
    val origin = origin
    val destination = destination
    fun transmit(message: Message) {
        if(message in origin.messages) {
            destination.messages.add(message)
            origin.messages.remove(message)
        }
    }
}
