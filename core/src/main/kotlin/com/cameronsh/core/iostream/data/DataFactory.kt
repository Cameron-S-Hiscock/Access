package com.cameronsh.core.iostream.data

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.data.*
import com.cameronsh.core.iostream.task.Task

object DataFactory {
    public val id: UUID = Id.genId()
    fun create(
        name: String = "data",
        input: UUID,
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
