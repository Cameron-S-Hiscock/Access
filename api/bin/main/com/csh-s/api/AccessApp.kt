package com.csh_s.api

import com.csh_s.utils.Id
import java.util.UUID

import java.io.File

class AccessApp(
    val name: String = "AccessApp",
    val indexHTML: File,
    val indexJVM: File,
) {
    val id: UUID = Id.genId(this)
}