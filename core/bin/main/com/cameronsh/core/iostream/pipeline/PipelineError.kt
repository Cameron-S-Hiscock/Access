package com.cameronsh.core.iostream.pipeline

import java.util.UUID

sealed class PipelineError {
    data class InvalidOrigin(val originId: UUID) : PipelineError()
    data class InvalidDestination(val destinationId: UUID) : PipelineError()
    data class FailedDelivery(val deliveryId: UUID) : PipelineError()
    data class UnbuiltState(val state: PipelineState) : PipelineError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : PipelineError()
    data class ClosedState(val state : PipelineState) : PipelineError()
    data class EmptyField(val fieldName: String) : PipelineError()
}