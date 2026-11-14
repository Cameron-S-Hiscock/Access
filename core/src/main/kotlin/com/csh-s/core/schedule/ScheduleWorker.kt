package com.csh_s.core.schedule

import com.csh_s.utils.Id
import java.util.UUID

import java.util.concurrent.*
import java.lang.Thread
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.task.TaskState
import com.csh_s.core.iostream.task.TaskPriority.*
import com.csh_s.core.iostream.task.TaskFactory
import com.csh_s.core.schedule.ScheduleService
import com.csh_s.core.workers.Worker
import com.csh_s.core.execution.ExecutionWorker
import com.csh_s.core.RSEQueue

class ScheduleWorker(
    val name: String = "ScheduleWorker",
    private val rseQueue: RSEQueue,
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
    private val scheduleService = ScheduleService(rseQueue = rseQueue)

    suspend fun scheduleTask(task: Task) {
        if(task.state == TaskState.REGISTERED) {
            scheduleService.scheduleTask(task)
        }
    }

    private val scheduleRSETasks = taskFactory.create(name = "scheduleRSETasks") {
        while(true) {
            for(task in rseQueue) {
                scheduleTask(task)
            }
        }
    }

    override suspend fun start() {
        addWork(scheduleRSETasks)
    }
}
