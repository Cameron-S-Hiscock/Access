package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID
import com.google.common.collect.BiMap

import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.message.Message

class IOStream(
    originId: UUID,
    destinationId: UUID,
) {
    val id: UUID = Id.genId(this)
    val origin = Id.objOf(originId)
    val destination = Id.objOf(destinationId)
    val ports = arrayOf(origin, destination)

    /*
    
    TODO Move commented code to Port logic

    */
}
