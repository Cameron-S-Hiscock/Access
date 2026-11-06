package com.csh_s.api

import com.csh_s.utils.Id
import java.util.UUID

class AppLoader(val appPath: String) {
    val id: UUID = Id.genId(this)

    fun load(): APIResult? {
        return null
    }
}