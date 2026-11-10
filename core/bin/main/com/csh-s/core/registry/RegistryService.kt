package com.csh_s.core.registry

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.registry.RegistryRepository
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.schedule.ScheduleService
import com.csh_s.core.Controller
import com.csh_s.core.iostream.task.TaskState.*
import java.util.concurrent.LinkedBlockingDeque
import com.csh_s.core.RSEQueue

class RegistryService(
    private val rseQueue: RSEQueue,
) {
    private val registryRepository = RegistryRepository()
    val id: UUID = Id.genId(this)
    
    suspend fun registerTask(task: Task) {
        task.state = REGISTERED
    }
}
