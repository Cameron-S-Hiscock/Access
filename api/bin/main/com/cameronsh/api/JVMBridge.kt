package com.cameronsh.api

import com.cameronsh.utils.Id
import java.util.UUID

class JVMBridge() {
    val id: UUID = Id.genId(this)
}