package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.message.Message
import java.util.concurrent.LinkedBlockingDeque

class IOStream(
    val name: String = "IOStream",
    val targets: Array<UUID?>,
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

        var i = 0
        for(target in targets) {
            if(target != null) {
                val port = Port(
                    name = "${name}Port${i}",
                    host = target,
                )
            }
            i++
        }
        i = 0
        while(i < ports.size) {
            val pipeline0 = Pipeline(
                name = "${ports[i].name}-${ports[i+1].name}Pipeline",
                origin = ports[i],
                destination = ports[i+1],
            )
            ports[i].targets.putIfAbsent(ports[i+1].id, pipeline0)
            val pipeline1 = Pipeline(
                name = "${ports[i+1].name}-${ports[i].name}Pipeline",
                origin = ports[i+1],
                destination = ports[i],
            )
            ports[i+1].targets.putIfAbsent(ports[i].id, pipeline1)
            i += 2
        }
    }

    fun send(target: UUID?, message: Message) {
        if(target in targets && target != null) {
            for(port in ports) {
                if(port.host == target) {
                    port.send(target, message)
                }
            }
        }
    }

    fun receive(target: UUID?): Message? {
        if(target in targets && target != null) {
            for(port in ports) {
                if(port.host == target) {
                    val message = port.receive()
                    if(message != null) {
                        return message
                    }
                }
            }
        }
        return null
    }
}
