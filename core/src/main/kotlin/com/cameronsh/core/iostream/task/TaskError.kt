package com.cameronsh.core.iostream.task

import java.util.UUID
import com.cameronsh.core.iostream.IOError

sealed class TaskError : IOError() {
    data class ErrorProneAction(val exception: Exception) : TaskError()
    data class FailedDataIO(val dataId: UUID) : TaskError()
}