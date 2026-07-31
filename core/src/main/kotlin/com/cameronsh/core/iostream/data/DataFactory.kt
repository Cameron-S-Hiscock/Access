package com.cameronsh.core.iostream.data

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.data.*
import com.cameronsh.core.iostream.task.Task

object DataFactory {
    val id: UUID = Id.genId(this)
    fun create(
        name: String = "data",
        input: Task,
        data: String = ""
    ): Data {
        val data = Data(
            name = name,
            input = input,
            data = data
        )
        return data
    }
}
