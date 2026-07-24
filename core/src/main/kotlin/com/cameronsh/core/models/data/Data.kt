package com.cameronsh.core.models.data

import com.cameronsh.utils.Id
import java.util.UUID
import com.cameronsh.utils.JSONSerializer

import com.cameronsh.core.models.task.Task

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Data(
    val name: String = "Data",
    @Contextual
    val input: UUID,
    val data: String = ""
) {
    @Contextual
    public val id: UUID = Id.genId()
    val json = JSONSerializer.encode(data)
    fun getJSONData(): String = JSONSerializer.decode(json)
}
