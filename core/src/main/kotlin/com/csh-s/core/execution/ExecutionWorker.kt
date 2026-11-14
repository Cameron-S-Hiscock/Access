package com.csh_s.core.execution

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.workers.Worker
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.task.TaskState
import com.csh_s.core.iostream.task.TaskFactory
import java.util.concurrent.LinkedBlockingDeque
import com.csh_s.core.RSEQueue

class ExecutionWorker(
    val name: String = "ExecutionWorker",
    private val rseQueue: RSEQueue,
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
    private val executionService = ExecutionService(rseQueue = rseQueue)

    suspend fun executeTask(task: Task) {
        if(task.state == TaskState.SCHEDULED || task.state == TaskState.PAUSED) {
            executionService.executeTask(task)
        }
    }

    private val executeRSETasks = taskFactory.create(name = "executeRSETasks") {
        while(true) {
            for(task in rseQueue) {
                executeTask(task)
            }
        }
    }

    override suspend fun start() {
        addWork(executeRSETasks)
    }
}
