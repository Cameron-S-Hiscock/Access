package com.cameronsh.api.bridges

import com.cameronsh.utils.Id
import java.util.UUID

class WebBridge() {
    val id: UUID = Id.genId(this)
}
