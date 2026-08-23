package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.registry.RegistryRepository
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.Controller
import com.cameronsh.core.iostream.task.TaskState.*

object RegistryService {
    private val registryRepository = RegistryRepository()
    private val scheduleService = ScheduleService()
    val id: UUID = Id.genId(this)
    
    fun registerTask(task: Task) {
        registryRepository.tasks.add(task)
        task.state = REGISTERED
        println("Registered task: ${task.name}")
        scheduleService.scheduleTask(task)
    }
}
