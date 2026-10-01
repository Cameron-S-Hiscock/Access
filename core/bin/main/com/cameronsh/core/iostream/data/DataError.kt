package com.cameronsh.core.iostream.data

import com.cameronsh.utils.Error

sealed class DataError : Error() {
    data class FailedSerialization(val json: String) : DataError()
    data class EmptyField(val fieldName: String) : DataError()
}