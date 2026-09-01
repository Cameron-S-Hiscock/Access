package com.cameronsh.systems

import com.cameronsh.utils.Id
import java.util.UUID

import java.io.File
import java.lang.foreign.*
import java.lang.invoke.MethodHandle
import com.cameronsh.systems.IOStreamBridge

object SystemsBridge {
    val id: UUID = Id.genId(this)

    val linker = Linker.nativeLinker()
    val arena = Arena.ofShared()
    val lookup = SymbolLookup.libraryLookup(NativeLoader.resolveLibraryPath(), arena)

    fun handle(name: String, desc: FunctionDescriptor): MethodHandle = 
        linker.downcallHandle(
            lookup.find(name).orElseThrow { UnsatisfiedLinkError("Symbol '$name' not found") },
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

    private val freeStrHandle = handle(
        "free_str", FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )
}
