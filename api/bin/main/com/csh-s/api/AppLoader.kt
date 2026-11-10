package com.csh_s.api

import com.csh_s.utils.Id
import java.util.UUID

import java.util.concurrent.CopyOnWriteArrayList
import java.io.File

class AppLoader(val appPath: String) {
    val id: UUID = Id.genId(this)

    val loadedApps = CopyOnWriteArrayList<AccessApp>

    fun load(app: AccessApp): APIResult? {
        if(app in loadedApps) {
            return APIResult(error = AppLoadError.AlreadyLoadedApp(app.id))
        }
        return null
    }
}
