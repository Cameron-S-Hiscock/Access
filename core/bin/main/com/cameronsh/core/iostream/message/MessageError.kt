package com.cameronsh.core.iostream.message

import java.util.UUID

sealed class MessageError {
    data class UnknownTarget(val targetId: UUID) : MessageError()
    data class InvalidAuthor(val authorId: UUID) : MessageError()
    data class DeliveryFailed(val reason: String) : MessageError()
    data class EmptyField(val fieldName: String) : MessageError()
}