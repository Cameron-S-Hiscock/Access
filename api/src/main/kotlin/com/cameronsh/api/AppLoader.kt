package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

class AppLoader(val appPath: String) {
    val id: UUID = Id.genId(this)

    fun load(): APIResult? {
        return null
    }
}