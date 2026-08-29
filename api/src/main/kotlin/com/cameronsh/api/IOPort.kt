package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.data.Data
import com.cameronsh.systems.IOStreamBridge

class IOPort(
    val host: UUID,
) {
    val id: UUID = Id.genId(this)

    lateinit var iostream: UUID

    fun send(destination: UUID, task: Task?, data: Data?) {
        val message = IOStreamBridge.create_message(
            id,
            destination,
            task,
            data,
        )
    }
}
