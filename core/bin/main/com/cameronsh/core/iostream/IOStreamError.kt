package com.cameronsh.core.iostream

import java.util.UUID

sealed class IOStreamError {
    data class InvalidTarget(val targetId: UUID?) : IOStreamError()
    data class NullComponent(val component: IOComponent) : IOStreamError()
    data class InvalidAuthorPair(val authorId: UUID?) : IOStreamError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : IOStreamError()
    data class EmptyField(val fieldName: String) : IOStreamError()
}