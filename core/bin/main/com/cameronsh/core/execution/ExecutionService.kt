package com.csh_s.core.execution

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.schedule.ScheduleWorker
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.task.TaskState
import java.util.concurrent.LinkedBlockingDeque
import com.csh_s.core.RSEQueue

class ExecutionService(
    private val rseQueue: RSEQueue,
) {
    private val executionRepository = ExecutionRepository()
    val id: UUID = Id.genId(this)

    suspend fun executeTask(task: Task) {
        task.state = TaskState.RUNNING
        try {
            task.action()
            task.state = TaskState.COMPLETED
        } catch(e: Exception) {
            println("Task ${task.name} failed: ${e.message}")
            task.state = TaskState.FAILED
        }
    }

    fun pauseTask(task: Task) {
        if(task.state == TaskState.RUNNING) {
            task.state = TaskState.PAUSED
        }
    }
}
