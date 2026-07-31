package com.cameronsh.core.iostream.data

import com.cameronsh.utils.Id
import java.util.UUID
import com.cameronsh.utils.JSONSerializer

import com.cameronsh.core.iostream.task.Task

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Data(
    val name: String = "Data",
    @Contextual
    val input: Task,
    val data: String = ""
) {
    @Contextual
    val id: UUID = Id.genId(this)
    val json = JSONSerializer.encode(data)
    fun getJSONData(): String = JSONSerializer.decode(json)
}
