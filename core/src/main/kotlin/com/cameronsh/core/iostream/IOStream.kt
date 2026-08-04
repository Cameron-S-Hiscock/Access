package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID
import com.google.common.collect.BiMap

import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.port.PortFactory
import com.cameronsh.core.iostream.port.PortType.*
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.pipeline.PipelineFactory

class IOStream(
    originId: UUID,
    destinationId: UUID,
) {
    val id: UUID = Id.genId(this)
    val origin = Id.objOf(originId)
    val destination = Id.objOf(destinationId)
    val ports: Array<Port?> = arrayOfNulls(2)
    val pipelines: Array<Pipeline?> = arrayOfNulls(2)
    init {
        val portToDestination = PortFactory.create(
            hostId = originId,
            iostreamId = id,
            type = STDIO,
            toDestination = true,
        )
        ports[0] = portToDestination
        val portToOrigin = PortFactory.create(
            hostId = destinationId,
            iostreamId = id,
            type = STDIO,
            toDestination = false,
        )
        ports[1] = portToOrigin
        val originDestinationPipe = PipelineFactory.create(
            originId = portToDestination.id,
            destinationId = portToOrigin.id,
        )
        pipelines[0] = originDestinationPipe
        val destinationOriginPipe = PipelineFactory.create(
            originId = portToOrigin.id,
            destinationId = portToDestination.id,
        )
        pipelines[1] = destinationOriginPipe
    }
    val originMessages = ArrayDeque<Message>(256)
    val destinationMessages = ArrayDeque<Message>(256)
}
