package com.cameronsh.core.iostream.message

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.message.Message

object MessageFactory {
    public val id: UUID = Id.genId()

    fun create(message: Message) {

    }
}
