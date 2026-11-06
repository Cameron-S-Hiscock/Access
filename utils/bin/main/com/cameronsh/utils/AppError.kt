package com.csh_s.utils

import java.util.UUID

interface AppError {
    data class EmptyField(val fieldName: String) : AppError
    data class UnknownError(val id: UUID?) : AppError
}