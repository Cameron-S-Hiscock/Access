package com.cameronsh.core.iostream.message

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.message.Message

object MessageFactory {
    init { Id.genId(this) }

    fun create(message: Message) {

    }
}
