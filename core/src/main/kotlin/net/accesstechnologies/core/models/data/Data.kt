package net.accesstechnologies.core.models.data

import net.accesstechnologies.utils.Id

data class data(
    val type: String
) {
    val id: String = Id.genId()
}
