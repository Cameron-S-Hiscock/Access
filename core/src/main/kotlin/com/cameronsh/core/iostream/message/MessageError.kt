package com.cameronsh.core.iostream.message

import java.util.UUID
import com.cameronsh.utils.Error

sealed class MessageError : Error() {
    data class UnknownTarget(val targetId: UUID) : MessageError()
    data class InvalidAuthor(val authorId: UUID) : MessageError()
    data class NullMessage(val state: MessageState?) : MessageError()
    data class DeliveryFailed(val reason: String) : MessageError()
    data class EmptyField(val fieldName: String) : MessageError()
}