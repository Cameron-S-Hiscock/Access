package com.csh_s.core.schedule

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.schedule.ScheduleRepository
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.task.TaskPriority.*
import com.csh_s.core.registry.RegistryService
import com.csh_s.core.iostream.task.TaskState.*
import com.csh_s.core.Controller
import java.util.concurrent.LinkedBlockingDeque
import com.csh_s.core.RSEQueue

internal class ScheduleService(
    private val rseQueue: RSEQueue,
) {
    private val scheduleRepository = ScheduleRepository()
    val id: UUID = Id.genId(this)
    
    suspend fun scheduleTask(task: Task) {
        task.state = SCHEDULED
    }
}

