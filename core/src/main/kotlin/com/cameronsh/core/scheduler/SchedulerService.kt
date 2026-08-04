package com.cameronsh.core.scheduler

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.scheduler.SchedulerRepository
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskPriority
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.Controller

object SchedulerService {
    private val schedulerRepository = SchedulerRepository()
    val id: UUID = Id.genId(this)
    
    fun scheduleTask(task: Task) {
        if(task.state == REGISTERED) {
            schedulerRepository.tasks.add(task)
            task.state = SCHEDULED
            println("Scheduled task: ${task.name}")
        } else {
            println("${task.name} not registered")
        }
    }

    fun getSchedule(): ArrayList<Task> = schedulerRepository.tasks
}

