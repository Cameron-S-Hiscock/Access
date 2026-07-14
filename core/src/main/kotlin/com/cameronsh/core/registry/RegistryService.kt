package com.cameronsh.core.registry

import com.cameronsh.utils.Id

import com.cameronsh.core.registry.RegistryRepository
import com.cameronsh.core.models.tasks.Task
import com.cameronsh.core.scheduler.SchedulerService

object RegistryService {
    private val registryRepository = RegistryRepository(100)
    val id: String = Id.genId()
    
    fun registerTask(task: Task): Boolean {
        val idx = registryRepository.tasks.indexOfFirst { it == null }
        if(idx != -1) {
            registryRepository.tasks[idx] = task
            SchedulerService.scheduleTask(task)
            return true
        } else {
            return false
        }
    }
}
