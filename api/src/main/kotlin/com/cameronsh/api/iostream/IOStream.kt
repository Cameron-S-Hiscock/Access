package com.cameronsh.api.iostream

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.api.ports.*

class IOStream(portAType: String, protBType: String) {
    public val id = Id.genId()
}
