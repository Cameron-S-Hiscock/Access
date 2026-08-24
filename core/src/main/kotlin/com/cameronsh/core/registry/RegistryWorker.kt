package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.schedule.ScheduleWorker

class RegistryWorker(
    name: String = "RegistryWorker",
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
    private val registryService = RegistryService()

    fun registerTask(task: Task) {
        registryService.registerTask(task)
    }

    fun run() {
        while(true) {
            for(task in registryService.getRegister()) {
                require(task != null)
                if(task.state in arrayOf(COMPLETED, FAILED, CANCELLED)) {
                    registryService.removeTask(task)
                }
            }
        }
    }
}
