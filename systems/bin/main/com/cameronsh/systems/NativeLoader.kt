package com.cameronsh.systems

import com.cameronsh.utils.Id
import java.util.UUID

import java.nio.file.*
import java.lang.foreign.*

object NativeLoader {
    val id: UUID = Id.genId(this)

    private val osName = System.getProperty("os.name").lowercase()
    private val archName = System.getProperty("os.arch").lowercase()

    private val platformDir: String = when {
        osName.contains("win") -> "windows"
        osName.contains("mac") -> "macos"
        osName.contains("nux") || osName.contains("nix") -> "linux"
        else -> error("Unsupported OS: $osName")
    }

    private val arch: String = when {
        archName.contains("aarch64") || archName.contains("arm64") -> "aarch64"
        archName.contains("amd64") || archName.contains("x86_64") -> "x86_64"
        else -> error("Unsupported arch: $archName")
    }

    private val libFileName: String = when (platformDir) {
        "windows" -> "systems.dll"
        "macos"   -> "libsystems.dylib"
        "linux"   -> "libsystems.so"
        else -> error("Could not find systems library")
    }

    fun resolveLibraryPath(): Path {
        val resourcePath = "/native/$platformDir/$arch/$libFileName"
        val cl = javaClass.classLoader
        println("Classpath entries with 'native': " + cl.javaClass.name)
        val stream = javaClass.getResourceAsStream(resourcePath)
            ?: error("Native library not found for $platformDir/$arch at $resourcePath")
        val tempFile = Files.createTempFile("rs", libFileName)
        tempFile.toFile().deleteOnExit()
        stream.use { input -> Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING) }
        return tempFile
    }
}
