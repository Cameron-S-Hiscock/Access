package com.cameronsh.core.workers

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.iostream.task.TaskState.*

class RegistryWorker(
    name: String = "RegistryWorker",
    private val registryService: RegistryService,
    private val taskFactory: TaskFactory,
) : Worker(name = name) {
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
