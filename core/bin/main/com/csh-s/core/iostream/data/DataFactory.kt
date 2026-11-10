package com.csh_s.core.iostream.data

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.iostream.task.Task

class DataFactory {
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
