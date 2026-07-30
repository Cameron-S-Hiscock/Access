package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.message.Message

class IOStream(
    origin: UUID,
    destination: UUID,
) {
    init { Id.genId(this) }
    val ports: Array<Port> = arrayOf()

    /*
    
    TODO Move commented code to Port logic

    val sent: Array<Message?> = arrayOfNulls(256)
    val recieved: Array<Message?> = arrayOfNulls(256)
    fun send(message: Message): Boolean {
        val idx = sent.indexOfFirst { it == null }
        if(idx != -1) {
            sent[idx] = message
            return true
        }
        return false
    }
    fun recieve(): Message? {
        val idx = recieved.indexOfFirst { it == null }
        if(idx != -1) {
            return recieved[idx]
        }
        return null
    }
    */
}
