package com.cameronsh.web

import com.cameronsh.utils.Id
import java.util.UUID

import java.io.File

object WebBridge {
    val id: UUID = Id.genId()

    fun runNodeScript(scriptPath: String, vararg args: String): String {
        val process = ProcessBuilder("node", scriptPath, *args)
            .directory(File("web/src"))
            .redirectErrorStream(true)
            .start()

        val output = process.inputStream.bufferedReader().readText()
        process.waitFor()
        return output
    }
}
