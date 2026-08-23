package com.cameronsh.utils

import com.cameronsh.utils.Id
import java.util.UUID

abstract class Factory<T> {
    val id: UUID = Id.genId(this)

    abstract fun create(): T
}
