package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

data class Data(
    val name: String,
    val type: String
) {
    val id: String = Id.genId()
}
