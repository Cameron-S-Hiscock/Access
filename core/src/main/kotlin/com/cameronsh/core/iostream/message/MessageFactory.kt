package com.cameronsh.core.iostream.message

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.data.Data
import com.cameronsh.core.iostream.task.Task

object MessageFactory {
    val id: UUID = Id.genId(this)

    fun create(
        data: Data?,
        task: Task?,
        originId: UUID,
        destinationId: UUID,
    ): Message {
        val message = Message(
            data = data,
            task = task,
            originId = originId,
            destinationId = destinationId,
        )
        return message
    }
}
