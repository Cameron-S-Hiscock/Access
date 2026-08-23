package com.cameronsh.core.schedule

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.schedule.ScheduleRepository
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskPriority
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.Controller

class ScheduleService {
    private val scheduleRepository = ScheduleRepository()
    val id: UUID = Id.genId(this)
    
    fun scheduleTask(task: Task) {
        if(task.state == REGISTERED) {
            scheduleRepository.tasks.add(task)
            task.state = SCHEDULED
            println("Scheduled task: ${task.name}")
        } else {
            println("${task.name} not registered")
        }
    }

    fun getSchedule(): ArrayList<Task> = scheduleRepository.tasks
}

