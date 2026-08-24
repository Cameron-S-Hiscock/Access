package com.cameronsh.core.schedule

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.*
import java.lang.Thread
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.registry.RegistryWorker
import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.workers.Worker

class ScheduleWorker(
    name: String = "ScheduleWorker",
    private val registryWorker: RegistryWorker,
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
    private val scheduleService = ScheduleService(),

    fun run() {
        while(true) {
            for(task in registryService.getRegister()) {
                require(task != null)
                if(task.state == REGISTERED) {
                    addWork(taskFactory.create(
                        name = "ScheduleWorkerTask: Schedule: $task.name",
                        priority = task.priority,
                    action = { scheduleService.scheduleTask(task) },
                    ))
                }   
                task.state = SCHEDULED
            }
        }
    }
}
