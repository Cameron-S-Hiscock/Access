package com.csh_s.app

import com.csh_s.utils.Id
import java.util.UUID

class Instance() {
    val id: UUID = Id.genId(this)
}
