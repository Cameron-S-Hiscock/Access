package com.cameronsh.core.iostream

import java.util.UUID
import com.cameronsh.core.iostream.IOError

sealed class IOStreamError : IOError() {
    data class InvalidTarget(val targetId: UUID?) : IOStreamError()
    data class NullComponent(val component: IOComponent) : IOStreamError()
    data class InvalidIOStreamState(val state: IOStreamState) : IOStreamError()
    data class InvalidAuthorPair(val authorId: UUID?) : IOStreamError()
    data class CriticalPrioritySpam(val criticalTasks: Int) : IOStreamError()
}