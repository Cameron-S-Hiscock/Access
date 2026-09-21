package com.cameronsh.core.iostream.port

import java.util.UUID

sealed class PortError {
    data class InvalidHost(val hostId: UUID) : PortError()
    data class UnbuiltState(val state: PortState) : PortError()
    data class ClosedState(val state: PortState) : PortError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : PortError()
    data class InvalidPipeline(val pipelineId: UUID) : PortError()
    data class EmptyField(val fieldName: String) : PortError()
}