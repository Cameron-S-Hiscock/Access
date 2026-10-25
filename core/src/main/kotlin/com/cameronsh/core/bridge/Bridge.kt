package com.cameronsh.core.bridge

import java.util.UUID

import com.cameronsh.core.iostream.message.Message
import com.cameronsh.core.iostream.IOResult

interface Bridge {
    val id: UUID
    val name: String
    suspend fun send(authorId: UUID, message: Message): IOResult?
    suspend fun receive(authorId: UUID): Message?
    suspend fun init()
}