package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.registry.RegistryRepository
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.scheduler.SchedulerService
import com.cameronsh.core.Controller
import com.cameronsh.core.iostream.message.MessageFactory

object RegistryService {
    private val registryRepository = RegistryRepository(100)
    val id: UUID = Id.genId(this)
    
    fun registerTask(task: Task): Boolean {
        val idx = registryRepository.tasks.indexOfFirst { it == null }
        if(idx != -1) {
            registryRepository.tasks[idx] = task
            println("Registered task: ${task.name}")
            SchedulerService.scheduleTask(task)
            return true
        }
        return false
    }
}
