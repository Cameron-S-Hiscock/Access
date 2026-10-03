package com.cameronsh.core.iostream.port

import java.util.UUID
import com.cameronsh.core.iostream.message.MessageState
import com.cameronsh.core.iostream.IOError

sealed class PortError : IOError() {
    data class InvalidHost(val hostId: UUID) : PortError()
    data class InvalidTarget(val targetId: UUID?) : PortError()
    data class UnbuiltState(val state: PortState) : PortError()
    data class ClosedState(val state: PortState) : PortError()
    data class InvalidMessageState(val state: MessageState?) : PortError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : PortError()
    data class InvalidPipeline(val pipelineId: UUID) : PortError()
    data class EmptyField(val fieldName: String) : PortError()
}