package com.cameronsh.ui

import java.util.UUID

abstract class UIError() {
    data class EmptyField(val fieldName: String) : UIError()
    data class UnknownError(val id: UUID?) : UIError()
}