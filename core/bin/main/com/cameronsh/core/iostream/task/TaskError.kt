package com.cameronsh.core.iostream.task

import java.util.UUID

sealed class TaskError {
    data class ErrorProneAction(val exception: Exception) : TaskError()
    data class FailedDataIO(val dataId: UUID) : TaskError()
    data class EmptyField(val fieldName: String) : TaskError()
}