package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

import com.cameronsh.utils.JSONSerializer

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Data(
    val name: String = "data",
    val data: String = ""
) {
    val id: String = Id.genId()
    val json = JSONSerializer.encode(data)
    fun getData(): String = JSONSerializer.decode(json)
}
