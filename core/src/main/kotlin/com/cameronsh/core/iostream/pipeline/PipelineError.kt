package com.cameronsh.core.iostream.pipeline

import java.util.UUID
import com.cameronsh.core.iostream.message.MessageState
import com.cameronsh.utils.Error

sealed class PipelineError : Error() {
    data class InvalidOrigin(val originId: UUID) : PipelineError()
    data class InvalidDestination(val destinationId: UUID) : PipelineError()
    data class InvalidMessageState(val state: MessageState?) : PipelineError()
    data class FailedDelivery(val deliveryId: UUID) : PipelineError()
    data class UnbuiltState(val state: PipelineState) : PipelineError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : PipelineError()
    data class ClosedState(val state : PipelineState) : PipelineError()
    data class EmptyField(val fieldName: String) : PipelineError()
}