package com.cameronsh.web

import java.io.File

object WebBridge {
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
