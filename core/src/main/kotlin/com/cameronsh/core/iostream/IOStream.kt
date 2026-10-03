package com.cameronsh.core.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.pipeline.Pipeline
import com.cameronsh.core.iostream.port.Port
import com.cameronsh.core.iostream.port.PortError
import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.message.MessageError
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

    suspend fun send(target: UUID? = null, message: Message, author: UUID? = null): IOResult {
        if(state != IOStreamState.OPEN) {
            return IOResult(error = IOStreamError.InvalidIOStreamState(state))
        }
        if(author == null && target == null) {
            return IOResult(error = IOStreamError.InvalidTarget(null))
        }

        if(author != null) {
            val destinationHost = IOStreamAuthorTable.pairs[author]
            if(destinationHost == null) {
                return IOResult(error = IOStreamError.InvalidAuthorPair(author))
            }
            val origin = ports.firstOrNull { it.host == author }
            val destination = ports.firstOrNull { it.host == destinationHost }
            if(origin == null || destination == null) {
                return IOResult(error = IOStreamError.InvalidTarget(null))
            }
            message.state = MessageState.REGISTERED
            message.state = MessageState.SCHEDULED
            val result = origin.send(destination.id, message)
            return result
        }

        if(target in targets && target != null) {
            val result = ports.firstOrNull { it.host == target }?.send(target, message)
            return result as IOResult
        }

        return IOResult(error = IOStreamError.InvalidTarget(null))
    }

    suspend fun receive(author: UUID? = null, target: UUID? = null): IOResult {
        var result: IOResult? = null
        if(author != null) {
            result = ports.firstOrNull { it.host == author }?.receive()
        } else if(target in targets && target != null) {
            result = ports.firstOrNull { it.host == target }?.receive()
        }

        if(result == null) {
            return IOResult(error = MessageError.NullMessage(null))
        }

        if(result.error is IOError && result.error != null) {
            val error = result.error
            when(error) {
                
                else -> { }
            }
        }

        if(result.success is IOSuccess.Value && result.success != null) {
            return IOResult(success = IOSuccess.Value(result.success.value))
        }

        return IOResult(error = IOError.UnknownError(null))
    }
}
