package net.accesstechnologies.core.launcher

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.registry.RegistryService
import net.accesstechnologies.core.models.tasks.*

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
