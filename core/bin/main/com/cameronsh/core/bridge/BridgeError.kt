package com.cameronsh.core.bridge

import java.util.UUID
import com.cameronsh.utils.Module
import com.cameronsh.core.iostream.IOComponent

sealed class BridgeError {
    data class InvalidTarget(val targetId: UUID?) : BridgeError()
    data class NullIOComponent(val component: IOComponent) : BridgeError()
    data class InvalidBridgeState(val state: BridgeState) : BridgeError()
    data class InvalidAuthorPair(val authorId: UUID?) : BridgeError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : BridgeError()
    data class CircularDependency(val module: Module) : BridgeError()
    data class EmptyField(val fieldName: String) : BridgeError()
}
