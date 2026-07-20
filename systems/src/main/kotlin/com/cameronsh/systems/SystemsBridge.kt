package com.cameronsh.systems

import java.io.File

object SystemsBridge {
    init {
        loadLibrary()
    }

    private fun loadLibrary() {
        val libName = when {
            System.getProperty("os.name").contains("Mac") -> "libsystems.dylib"
            System.getProperty("os.name").contains("Windows") -> "systems.dll"
            else -> "libsystems.so"
        }
        val resourcePath = "/native/$libName"

        val resourceStream = SystemsBridge::class.java.getResourceAsStream(resourcePath)?: error("Native library not found at $resourcePath")

        val tempFile = File.createTempFile(libName, null)
        tempFile.deleteOnExit()

        resourceStream.use { input -> 
            tempFile.outputStream().use { output -> input.copyTo(output) }
        }

        System.load(tempFile.absolutePath)
    }

    external fun systemsTask(input: String): String
}
