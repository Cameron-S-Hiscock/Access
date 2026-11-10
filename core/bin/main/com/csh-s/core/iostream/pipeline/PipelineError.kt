package com.csh_s.core.iostream.pipeline

import java.util.UUID
import com.csh_s.core.iostream.message.MessageState
import com.csh_s.core.iostream.IOError

sealed class PipelineError : IOError {
    data class InvalidOrigin(val originId: UUID) : PipelineError()
    data class InvalidDestination(val destinationId: UUID) : PipelineError()
    data class InvalidMessageState(val state: MessageState?) : PipelineError()
    data class FailedDelivery(val deliveryId: UUID) : PipelineError()
    data class UnbuiltState(val state: PipelineState) : PipelineError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : PipelineError()
    data class ClosedState(val state : PipelineState) : PipelineError()
}