package com.cameronsh.api.bridges

import com.cameronsh.utils.Id
import java.util.UUID

class JVMBridge() {
    val id: UUID = Id.genId(this)
}
