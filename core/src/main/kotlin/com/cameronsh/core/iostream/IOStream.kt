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

    val ports: MutableList<Port> = mutableListOf()

    init {
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
                name = "${ports[i].name}to${ports[i+1].name}Pipeline",
                origin = ports[i],
                destination = ports[i+1],
            )
            ports[i].targets.putIfAbsent(ports[i+1].id, pipeline0)
            val pipeline1 = Pipeline(
                name = "${ports[i+1].name}to${ports[i].name}Pipeline",
                origin = ports[i+1],
                destination = ports[i],
            )
            ports[i+1].targets.putIfAbsent(ports[i].id, pipeline1)
            i += 2
        }
    }

    fun send(target: UUID? = null, message: Message, author: UUID? = null) {
        if(author != null) {
            for(port in ports) {
                if(port.host == IOStreamAuthorTable.pairs[author]) {
                    port.send(IOStreamAuthorTable.pairs[author], message)
                }
            }
        }

        if(target in targets && target != null) {
            for(port in ports) {
                if(port.host == target) {
                    port.send(target, message)
                }
            }
        }
    }

    fun receive(author: UUID? = null, target: UUID? = null): Message? {
        if(author != null) {
            for(port in ports) {
                if(port.host == IOStreamAuthorTable.pairs[author]) {
                    port.receive()
                }
            }
        }

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
