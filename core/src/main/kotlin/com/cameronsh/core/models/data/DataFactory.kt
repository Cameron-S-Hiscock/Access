package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

import com.cameronsh.core.models.data.*

object DataFactory {
    val id: String = Id.genId()
    fun create(
        name: String = "data",
        type: String = "json"
    ): Data {
        val data = Data(
            name = name,
            type = type
        )
        return data
    }
}
