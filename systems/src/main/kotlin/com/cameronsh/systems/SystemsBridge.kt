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
}
