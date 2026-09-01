package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.port.Port

class IOStream(
    val name: String = "IOStream",
    val targets: Array<UUID>,
) {
    val id: UUID = Id.genId(this)
    private val processWorker = ProcessWorker(
        name = "${name}ProcessWorker",
        host = id,
    )

    val ports: List<Port> = listOf()

    init {
        try {
            require(targets.size == 2)
        } catch(e: Exception) {
            println("${name} failed initialization: ${e}")
        }

        for(target in targets) {
            
        }
    }
}
