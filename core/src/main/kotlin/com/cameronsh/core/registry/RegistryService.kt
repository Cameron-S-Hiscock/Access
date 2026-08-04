package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.registry.RegistryRepository
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.scheduler.SchedulerService
import com.cameronsh.core.Controller
import com.cameronsh.core.iostream.message.MessageFactory
import com.cameronsh.core.iostream.task.TaskState.*

object RegistryService {
    private val registryRepository = RegistryRepository()
    val id: UUID = Id.genId(this)
    
    fun registerTask(task: Task) {
        val idx = registryRepository.tasks.indexOfFirst { it == null }
        if(idx != -1) {
            registryRepository.tasks[idx] = task
            task.state = REGISTERED
            println("Registered task: ${task.name}")
            SchedulerService.scheduleTask(task)
        }
    }
}
