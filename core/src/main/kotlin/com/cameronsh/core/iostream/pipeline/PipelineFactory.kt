package com.cameronsh.core.iostream.pipeline

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.port.StdioPort

object PipelineFactory {
    val id: UUID = Id.genId(this)
    fun create(
        originId: UUID,
        destinationId: UUID,
    ): Pipeline {
        val pipeline = Pipeline(
            origin = Id.objOf(originId) as StdioPort,
            destination = Id.objOf(destinationId) as StdioPort,
        )
        return pipeline
    }
}
