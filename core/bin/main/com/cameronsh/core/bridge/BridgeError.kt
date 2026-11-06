package com.csh_s.core.bridge

import java.util.UUID
import com.csh_s.utils.Module
import com.csh_s.core.iostream.IOComponent
import com.csh_s.core.iostream.IOError

sealed class BridgeError : IOError {
    data class InvalidTarget(val targetId: UUID?) : BridgeError()
    data class NullIOComponent(val component: IOComponent) : BridgeError()
    data class InvalidBridgeState(val state: BridgeState) : BridgeError()
    data class InvalidAuthorPair(val authorId: UUID?) : BridgeError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : BridgeError()
    data class CircularDependency(val module: Module) : BridgeError()
}
