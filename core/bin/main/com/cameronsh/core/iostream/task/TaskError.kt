package com.cameronsh.core.iostream.task

import java.util.UUID
import com.cameronsh.utils.Error

sealed class TaskError : Error() {
    data class ErrorProneAction(val exception: Exception) : TaskError()
    data class FailedDataIO(val dataId: UUID) : TaskError()
    data class EmptyField(val fieldName: String) : TaskError()
}