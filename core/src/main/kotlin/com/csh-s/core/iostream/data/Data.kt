package com.csh_s.core.iostream.data

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.utils.JSONSerializer
import com.csh_s.core.iostream.task.Task
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
