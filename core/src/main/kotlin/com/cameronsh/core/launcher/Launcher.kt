package com.cameronsh.core.launcher

import com.cameronsh.utils.Id

import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.models.tasks.*

class Launcher(
    registryService: RegistryService
) {
    val id: String = Id.genId()

    fun runTask(task: Task): Boolean {
        if(task.state == TaskState.SCHEDULED || task.state == TaskState.PAUSED) {
            task.state = TaskState.RUNNING
            return true
        }
        return false
    }

    fun pauseTask(task: Task): Boolean {
        if(task.state == TaskState.RUNNING) {
            task.state = TaskState.PAUSED
            return true
        }
        return false
    }
}
