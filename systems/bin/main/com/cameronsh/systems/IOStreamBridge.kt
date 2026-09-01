package com.cameronsh.systems

import com.cameronsh.utils.Id
import java.util.UUID

import java.io.File
import java.lang.foreign.*
import java.lang.invoke.MethodHandle
import com.cameronsh.systems.SystemsBridge

object IOStreamBridge {
    val id: UUID = Id.genId(this)

    private val TARGETS_LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(2, MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_BYTE)).withName("values")
    ).withName("Targets")

    private val createIostreamHandle = SystemsBridge.handle(
        "create_iostream", FunctionDescriptor.of(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            TARGETS_LAYOUT,
        )
    )

    fun UUIDtoTargets(segment: MemorySegment, idx: Int, id: UUID) {
        val slice: MemorySegment = segment.asSlice(
            TARGETS_LAYOUT.byteOffset(
                MemoryLayout.PathElement.groupElement("values"),
                MemoryLayout.PathElement.sequenceElement(idx.toLong()),
            ),
            16,
        )
        slice.set(ValueLayout.JAVA_LONG_UNALIGNED, 0, id.mostSignificantBits)
        slice.set(ValueLayout.JAVA_LONG_UNALIGNED, 8, id.leastSignificantBits)
    }

    fun TargetstoUUID(segment: MemorySegment, idx: Int): UUID {
        val slice: MemorySegment = segment.asSlice(
            TARGETS_LAYOUT.byteOffset(
                MemoryLayout.PathElement.groupElement("values"),
                MemoryLayout.PathElement.sequenceElement(idx.toLong()),
            ),
            16,
        )
        val msb = slice.get(ValueLayout.JAVA_LONG_UNALIGNED, 0)
        val lsb = slice.get(ValueLayout.JAVA_LONG_UNALIGNED, 8)
        return UUID(msb, lsb)
    }

    fun create_iostream(ids: Array<UUID>) {
        val env: Any? = null
        val obj: Any? = null
        val targets: MemorySegment = SystemsBridge.arena.allocate(TARGETS_LAYOUT)
        UUIDtoTargets(targets, 0, ids[0])
        UUIDtoTargets(targets, 1, ids[1])
        Arena.ofShared().use { callArena ->
            createIostreamHandle.invoke(env, obj, targets)
        }
    }
}
