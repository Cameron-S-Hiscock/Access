package com.cameronsh.core.iostream

import java.util.UUID

abstract class IOError() {
    data class EmptyField(val fieldName: String) : IOError()
    data class UnknownError(val id: UUID?) : IOError()
}