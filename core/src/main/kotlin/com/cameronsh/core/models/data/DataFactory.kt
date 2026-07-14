package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

import com.cameronsh.core.models.data.*

object DataFactory {
    val id: String = genId()
    fun create(
        nam: String = "data"
        type: String = "json"
    ): Data = Data(
        name = name
        type = type
    )
}
