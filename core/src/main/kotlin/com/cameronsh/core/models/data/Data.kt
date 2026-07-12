package com.cameronsh.core.models.data

import com.cameronsh.utils.Id

data class data(
    val type: String
) {
    val id: String = Id.genId()
}
