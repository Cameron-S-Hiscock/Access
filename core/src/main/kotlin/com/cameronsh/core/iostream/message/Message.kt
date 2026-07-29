package com.cameronsh.core.iostream.message

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.pipeline.PipelineHook

data class Message(
    val name: String = "Message",
    val task: Task,
    val destination: String
): PipelineHook {
    val id: UUID = Id.genId()
    val origin: UUID = task.id
    override val message = id
}
