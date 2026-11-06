package com.csh_s.utils

import java.util.UUID

sealed class ValidationError {
    data class UnfilledValue(val fieldName: String): ValidationError()
}