package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

import java.io.File

class AccessApp(
    val name: String = "AccessApp",
    val indexHTML: File,
    val indexBack: File,
) {
    val id: UUID = Id.genId(this)
}