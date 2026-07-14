package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class data(
    val name: String
    val type: String
) {
    val id: String = Id.genId()
}
