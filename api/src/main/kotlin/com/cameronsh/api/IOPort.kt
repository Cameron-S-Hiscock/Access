package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

class IOPort(
    val host: UUID,
) {
    val id: UUID = Id.genId(this)

    lateinit var iostream: UUID
}
