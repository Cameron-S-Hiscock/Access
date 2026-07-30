package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.IOStream
import com.cameronsh.core.iostream.port.*

object IOStreamFactory {
    init { Id.genId(this) }
    fun create(
        origin: UUID,
        destination: UUID,
    ): IOStream {
        val iostream = IOStream(origin, destination)
        return iostream
    }
}
