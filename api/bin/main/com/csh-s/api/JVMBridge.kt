package com.csh_s.api

import com.csh_s.utils.Id
import java.util.UUID

class JVMBridge() {
    val id: UUID = Id.genId(this)
}