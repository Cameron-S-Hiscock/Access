package com.cameronsh.systems

import com.cameronsh.utils.Id
import java.util.UUID

import java.io.File
import java.lang.foreign.*
import java.lang.invoke.MethodHandle

object SystemsBridge {
    val id: UUID = Id.genId(this)

    val linker = Linker.nativeLinker()
    val arena = Arena.ofShared()
    val lookup = SymbolLookup.libraryLookup(NativeLoader.resolveLibraryPath(), arena)

    private fun handle(name: String, desc: FunctionDescriptor): MethodHandle = 
        linker.downcallHandle(
            lookup.find(name).orElseThrow { UnsatisfiedLinkError("symbol '$name' not found") },
            desc
        )

    /*
    private val initJvmHandle = handle(
        "systems_init_jvm", FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )

    fun SystemsInitJvm() {
        initJvmHandle.invokeExact(jvmPtr) as Unit
    }
    */

    private val addHandle = handle(
        "systems_add", FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
    )

    fun systems_add(a: Int, b: Int): Int = 
        addHandle.invoke(a, b) as Int

    private val logHandle = handle(
        "systems_log", FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
    )

    fun systems_log(message: String): String {
        Arena.ofConfined().use { callArena ->
            val msgSeg = callArena.allocateFrom(message)
            val resultPtr = logHandle.invoke(msgSeg) as MemorySegment
            val result = resultPtr.reinterpret(Long.MAX_VALUE).getString(0)
            freeStrHandle.invoke(resultPtr)
        return result
        }
    }

    private val freeStrHandle = handle(
        "free_str", FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )

    private val TARGETS_LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(2, MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_BYTE)).withName("values")
    ).withName("Targets")

    private val createIostreamHandle = handle(
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
        val targets: MemorySegment = arena.allocate(TARGETS_LAYOUT)
        UUIDtoTargets(targets, 0, ids[0])
        UUIDtoTargets(targets, 1, ids[1])
        Arena.ofShared().use { callArena ->
            createIostreamHandle.invoke(env, obj)
        }
    }
}
