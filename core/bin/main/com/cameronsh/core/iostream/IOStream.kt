package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.message.Message
import java.util.concurrent.LinkedBlockingDeque
import kotlinx.coroutines.*

class IOStream(
    val name: String = "IOStream",
    val targets: Array<UUID?>,
) {
    val id: UUID = Id.genId(this)
    private val processWorker = ProcessWorker(
        name = "${name}ProcessWorker",
        host = id,
    )

    private val ports: MutableList<Port> = mutableListOf()
    suspend fun getCache(author: UUID): LinkedBlockingDeque<Message>? {
        for(port in ports) {
            if(port.host == author) {
                return port.cache
            }
        }
        return null
    }

    suspend fun init() {
        withContext(Dispatchers.IO) {
        this.launch {
            var i = 0
            for(target in targets) {
                if(target != null) {
                    val port = Port(
                        name = "${name}Port${i}",
                        host = target,
                    )
                    ports.add(port)
                }
                i++
            }
            i = 0
            while(i < ports.size - 1) {
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
                i++
            }
        }
        }
    }

    suspend fun send(target: UUID? = null, message: Message, author: UUID? = null): Result<Unit> {
        if(author == null && target == null) {
            return Result.failure(IllegalArgumentException(IOStreamError.InvalidTarget(null).toString()))
        }

        if(author != null) {
            val destinationHost = IOStreamAuthorTable.pairs[author]
            if(destinationHost == null) {
                return Result.failure(IllegalArgumentException(IOStreamError.InvalidAuthorPair(author).toString()))
            }
            val origin = ports.firstOrNull { it.host == author } ?: return Result.success(Unit)
            val destination = ports.firstOrNull { it.host == destinationHost } ?: return Result.success(Unit)
            origin.send(destination.id, message)
            return Result.success(Unit)
        }

        if(target in targets && target != null) {
            ports.firstOrNull { it.host == target }?.send(target, message)
        }
        
        return Result.success(Unit)
    }

    suspend fun receive(author: UUID? = null, target: UUID? = null): Message? {
        var message: Message? = null
        if(author != null) {
            message = ports.firstOrNull { it.host == author }?.receive()
        } else if(target in targets && target != null) {
            message = ports.firstOrNull { it.host == target }?.receive()
        }
        return message
    }
}
