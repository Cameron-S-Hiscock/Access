package com.cameronsh.core.iostream.data

sealed class DataError {
    data class FailedSerialization(val json: String) : DataError()
    data class EmptyField(val fieldName: String) : DataError()
}