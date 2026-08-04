package com.cameronsh.core.iostream.pipeline

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.message.Message

interface PipelineHook {
    val message: UUID
}
