package com.csh_s.utils

import java.util.UUID

object Constants {
    const val APP_NAME: String = "Access"
    const val APP_VERSION: String = "0.1.0"
    val APP_ID: UUID = Id.genId(this)
    const val APP_OWNERS: String = "Cameron Hiscock"
    val APP_DEVELOPERS: Array<String> = arrayOf(
        "Cameron Hiscock",
    )
    const val COMPANY: String = "Simultaneity"

    const val WINDOW_WIDTH: Int = 1280
    const val WINDOW_HEIGHT: Int = 800
}
