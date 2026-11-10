package com.csh_s.core.iostream.message

import java.util.UUID
import com.csh_s.core.iostream.IOError

sealed class MessageError : IOError {
    data class UnknownTarget(val targetId: UUID) : MessageError()
    data class InvalidAuthor(val authorId: UUID) : MessageError()
    data class NullMessage(val state: MessageState?) : MessageError()
    data class DeliveryFailed(val reason: String) : MessageError()
}