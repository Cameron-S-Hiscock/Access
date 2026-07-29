package com.cameronsh.core.iostream.pipeline

import com.cameronsh.utils.Id
import java.util.UUID

interface PipelineHook {
    val message: UUID
}
