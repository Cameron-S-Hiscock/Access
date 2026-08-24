package com.cameronsh.core.execution

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.schedule.ScheduleWorker
import java.util.concurrent.LinkedBlockingDeque

class ExecutionWorker(
    val name: String = "ExecutionWorker",
    private val RSETasks: LinkedBlockingDeque<Task>,
    private val scheduleWorker: ScheduleWorker,
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
    private val executionService = ExecutionService(RSETasks = RSETasks)

    fun executeTask(task: Task) {
        executionService.executeTask(task)
    }
}
