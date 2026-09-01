package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

class IOStream(
    val name: String = "IOStream",
    val targets: Array<UUID>,
) {
    val id: UUID = Id.genId(this)

    init {
        try {
            require(targets.size == 2)
        } catch(e: Exception) {
            println("${name} failed initialization: ${e}")
        }
    }
}
