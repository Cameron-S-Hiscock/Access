package com.cameronsh.core.models.data

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.models.data.*
import com.cameronsh.core.models.task.Task

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
