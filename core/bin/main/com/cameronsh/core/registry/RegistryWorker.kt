package com.csh_s.core.registry

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.workers.Worker
import com.csh_s.core.registry.RegistryService
import com.csh_s.core.iostream.task.TaskFactory
import com.csh_s.core.iostream.task.TaskState
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.schedule.ScheduleWorker
import java.util.concurrent.LinkedBlockingDeque
import com.csh_s.core.RSEQueue

class RegistryWorker(
    val name: String = "RegistryWorker",
    private val rseQueue: RSEQueue,
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
    private val registryService = RegistryService(rseQueue = rseQueue)

    suspend fun registerTask(task: Task) {
        if(task.state == TaskState.PENDING) {
            registryService.registerTask(task)
        }
    }

    suspend fun registerRSETasks() {
        while(true) {
            for(task in rseQueue) {
                registerTask(task)
            }
        }
    }
}
