package com.cameronsh.core.iostream.data

import java.util.UUID
import com.cameronsh.core.iostream.IOError

sealed class DataError : IOError() {
    data class FailedSerialization(val json: String) : DataError()
    data class CorruptedData(val dataId: UUID) : DataError()
    data class UnknownDataFormat(val type: String) : DataError()
}