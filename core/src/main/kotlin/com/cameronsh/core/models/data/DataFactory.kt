package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

import com.cameronsh.core.models.data.*

object DataFactory {
    val id: String = genId()
    fun create(
        type: String = "array"
    ): Data = Data(
        type = type
    )
}
