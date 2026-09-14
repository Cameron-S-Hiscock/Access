package com.cameronsh.core.schedule

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.*
import java.lang.Thread
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.iostream.task.TaskPriority.*
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.workers.Worker
import com.cameronsh.core.execution.ExecutionWorker

class ScheduleWorker(
    val name: String = "ScheduleWorker",
    private val RSETasks: LinkedBlockingDeque<Task>,
    private val taskFactory: TaskFactory,
    private val executionWorker: ExecutionWorker,
) : Worker(name = name) {
    private val scheduleService = ScheduleService(RSETasks = RSETasks)

    fun scheduleTask(task: Task) {
        scheduleService.scheduleTask(task)
        executionWorker.executeTask(task)
    }
}
