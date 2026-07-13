package com.cameronsh.core.scheduler

import com.cameronsh.utils.Id

import com.cameronsh.core.scheduler.SchedulerRepository
import com.cameronsh.core.models.tasks.Task
import com.cameronsh.core.models.tasks.TaskPriority
import com.cameronsh.core.registry.RegistryService

object SchedulerService {
    private val schedulerRepository: SchedulerRepository = SchedulerRepository(100)
    val id: String = Id.genId()
    
    fun scheduleTask(task: Task): Boolean {
        if(task.priority == TaskPriority.CRITICAL) {
            val idx = schedulerRepository.critical.indexOfFirst { it == null }
            if(idx != -1) {
                schedulerRepository.critical[idx]
                return true
            }
            return false
        } else {
            val idx = schedulerRepository.tasks.indexOfFirst { it == null }
            if(idx != -1) {
                schedulerRepository.tasks[idx] = task
                return true
            }
            return false
        }
    }
}
