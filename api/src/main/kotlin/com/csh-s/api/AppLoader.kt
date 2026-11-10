package com.csh_s.api

import com.csh_s.utils.Id
import java.util.UUID

import java.util.concurrent.CopyOnWriteArrayList
import java.io.File
import java.io.FileNotFoundException
import java.util.Scanner

class AppLoader() {
    val id: UUID = Id.genId(this)

    val loadedApps = CopyOnWriteArrayList<AccessApp>()

    fun load(appDir: String): APIResult? {
        val indexHTML = File("${appDir}/index.html")
        val indexJVM = File("${appDir}/index.kt")

        return null
    }
}
