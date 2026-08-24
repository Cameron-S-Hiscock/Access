package com.cameronsh.core.execution

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.execution.ExecutionService
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.iostream.task.TaskFactory

class ExecutionWorker(
    name: String = "ExecutionWorker",
    private val scheduleService: ScheduleService,
    private val executionService: ExecutionService,
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
    fun run() {
        while(true) {
            for(task in scheduleService.getSchedule()) {
                if(task.state == SCHEDULED) {
                    addWork(taskFactory.create(
                        name = "ExecutionWorkerTask: Execution: $task.name",
                        priority = task.priority,
                        action = { executionService.executeTask(task) },
                    ))
                }
            }
        }
    }
}
