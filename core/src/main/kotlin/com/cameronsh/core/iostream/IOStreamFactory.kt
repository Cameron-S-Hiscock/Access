package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.IOStream
import com.cameronsh.core.iostream.port.*

object IOStreamFactory {
    val id: UUID = Id.genId(this)
    fun create(
        originId: UUID,
        destinationId: UUID,
    ): IOStream {
        val iostream = IOStream(originId, destinationId)
        return iostream
    }
}
