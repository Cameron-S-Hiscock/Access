package com.csh_s.core.bridge

import java.util.UUID

import com.csh_s.core.iostream.message.Message
import com.csh_s.core.iostream.IOResult

interface Bridge {
    val id: UUID
    val name: String
    suspend fun send(authorId: UUID, message: Message): IOResult?
    suspend fun receive(authorId: UUID): Message?
    suspend fun init()
}