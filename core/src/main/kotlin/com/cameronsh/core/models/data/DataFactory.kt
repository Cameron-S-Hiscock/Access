package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

import com.cameronsh.core.models.data.*

object DataFactory {
    val id: String = Id.genId()
    fun create(
        name: String = "data",
        data: String = ""
    ): Data {
        val data = Data(
            name = name,
            data = data
        )
        return data
    }
}
