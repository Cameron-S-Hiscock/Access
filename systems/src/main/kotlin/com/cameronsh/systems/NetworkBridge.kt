package com.cameronsh.systems

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.systems.SystemsBridge
import java.io.File
import java.lang.foreign.*
import java.lang.invoke.MethodHandle

object NetworkBridge {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("NetworkBridge", id) }

    private val serverNewHandle = SystemsBridge.handle(
        "server_new",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_LONG,
        )
    )

    private val serverFreeHandle = SystemsBridge.handle(
        "server_free",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )

    fun newServer(address: String): MemorySegment {
        val addressSegment = SystemsBridge.arena.allocateFrom(address)

        return serverNewHandle.invoke(
            addressSegment,
            id.mostSignificantBits,
            id.leastSignificantBits,
        ) as MemorySegment
    }

    fun freeServer(server: MemorySegment) {
        serverFreeHandle.invoke(server)
    }

    private val clientNewHandle = SystemsBridge.handle(
        "client_new",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_LONG,
        )
    )

    private val clientFreeHandle = SystemsBridge.handle(
        "client_free",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )

    fun newClient(address: String): MemorySegment {
        val addressSegment = SystemsBridge.arena.allocateFrom(address)

        return clientNewHandle.invoke(
            addressSegment,
            id.mostSignificantBits,
            id.leastSignificantBits,
        ) as MemorySegment
    }

    fun freeClient(client: MemorySegment) {
        clientFreeHandle.invoke(client)
    }
}