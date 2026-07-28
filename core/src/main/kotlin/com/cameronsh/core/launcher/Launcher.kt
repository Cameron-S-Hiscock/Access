package com.cameronsh.core.launcher

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.scheduler.SchedulerService
import com.cameronsh.core.iostream.task.*

class Launcher(
    schedulerService: SchedulerService
) {
    public val id: UUID = Id.genId()

    fun executeTask(task: Task?): Boolean {
        require(task != null)
        if(task.state == TaskState.SCHEDULED || task.state == TaskState.PAUSED) {
            task.state = TaskState.RUNNING
            // TODO Make Launcher run Task's action
            task.action.invoke()
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
    init {
    while(!schedulerService.getSchedule().isNullOrEmpty()) {
        val idx = schedulerService.getSchedule().indexOfFirst { it == null }
        if(idx != -1) {
            executeTask(schedulerService.getSchedule()[idx])
        }
    }
    }
}
