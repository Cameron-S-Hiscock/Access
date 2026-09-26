package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.message.MessageState
import java.util.concurrent.LinkedBlockingDeque
import kotlinx.coroutines.*

class IOStream(
    val name: String = "IOStream",
    val targets: MutableList<UUID?>,
) {
    val id: UUID = Id.genId(this)
    var state: IOStreamState = IOStreamState.PENDING
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
        if(state == IOStreamState.OPEN) return
        ports.clear()
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
        for(i in ports.indices) {
            for(j in i+1 until ports.size) {
                val a = ports[i]
                val b = ports[j]

                val aToB = Pipeline(
                    name = "${a.name}To${b.name}Pipeline",
                    origin = a,
                    destination = b,
                )
                val bToA = Pipeline(
                    name = "${b.name}To${a.name}",
                    origin = b,
                    destination = a,
                )
                a.targets[b.id] = aToB
                b.targets[a.id] = bToA
            }
        }
        for(port in ports) {
            port.init()
        }
        state = IOStreamState.OPEN
    }

    suspend fun send(target: UUID? = null, message: Message, author: UUID? = null): Result<Unit> {
        if(state != IOStreamState.OPEN) {
            return Result.failure(IllegalArgumentException(IOStreamError.InvalidIOStreamState(state).toString()))
        }
        if(author == null && target == null) {
            return Result.failure(IllegalArgumentException(IOStreamError.InvalidTarget(null).toString()))
        }

        if(author != null) {
            val destinationHost = IOStreamAuthorTable.pairs[author]
            if(destinationHost == null) {
                return Result.failure(IllegalArgumentException(IOStreamError.InvalidAuthorPair(author).toString()))
            }
            val origin = ports.firstOrNull { it.host == author }
            val destination = ports.firstOrNull { it.host == destinationHost }
            if(origin == null || destination == null) {
                return Result.failure(IllegalArgumentException(IOStreamError.InvalidTarget(null).toString()))
            }
            message.state = MessageState.REGISTERED
            message.state = MessageState.SCHEDULED
            origin.send(destination.id, message)
            return Result.success(Unit)
        }

        if(target in targets && target != null) {
            ports.firstOrNull { it.host == target }?.send(target, message)
                return Result.success(Unit)
        }

        return Result.failure(IllegalArgumentException(IOStreamError.InvalidTarget(null).toString()))
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
