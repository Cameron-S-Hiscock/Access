package com.cameronsh.core.scheduler

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.scheduler.SchedulerRepository
import com.cameronsh.core.models.task.Task
import com.cameronsh.core.models.task.TaskPriority
import com.cameronsh.core.registry.RegistryService

object SchedulerService {
    private val schedulerRepository: SchedulerRepository = SchedulerRepository(100)
    public val id: UUID = Id.genId()
    
    fun scheduleTask(task: Task): Boolean {
        val idx = schedulerRepository.tasks.indexOfFirst { it == null }
        if(idx != -1) {
            schedulerRepository.tasks[idx] = task
            println("Scheduled task: ${task.name}")
            return true
        }
        return false
    }

    fun getSchedule(): Array<Task?> = schedulerRepository.tasks
}

