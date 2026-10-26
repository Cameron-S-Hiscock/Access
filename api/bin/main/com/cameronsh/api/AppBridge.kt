package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

class AppBridge(val appPath: String) {
    val id: UUID = Id.genId(this)

    private val appLoader = AppLoader(appPath)
    val app = appLoader.load()
}