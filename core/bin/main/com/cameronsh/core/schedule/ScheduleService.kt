package com.cameronsh.core.schedule

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.schedule.ScheduleRepository
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskPriority.*
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.Controller
import java.util.concurrent.LinkedBlockingDeque

class ScheduleService(
    private val RSETasks: LinkedBlockingDeque<Task>,
) {
    private val scheduleRepository = ScheduleRepository()
    val id: UUID = Id.genId(this)
    
    fun scheduleTask(task: Task) {
        if(task.state == REGISTERED) {
            if(task.priority == CRITICAL) {
                RSETasks.remove(task)
                RSETasks.addFirst(task)
            } else {
                RSETasks.add(task)
            }
            task.state = SCHEDULED
            println("Scheduled task: ${task.name}")
        } else {
            println("${task.name} not registered")
        }
    }
}

