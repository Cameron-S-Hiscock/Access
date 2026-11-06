package com.csh_s.core.iostream.task

import java.util.UUID
import com.csh_s.core.iostream.IOError

sealed class TaskError : IOError {
    data class ErrorProneAction(val exception: Exception) : TaskError()
    data class FailedDataIO(val dataId: UUID) : TaskError()
}