package com.cameronsh.core.launcher

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.scheduler.SchedulerService
import com.cameronsh.core.iostream.task.*
import com.cameronsh.core.iostream.task.TaskState.*

class Launcher() {
    val id: UUID = Id.genId(this)

    fun executeTask(task: Task?) {
        require(task != null)
        if(task.state == SCHEDULED || task.state == PAUSED) {
            task.state = RUNNING
            println("Executing task: ${task.name}")
            task.action.invoke()
        }
    }

    fun pauseTask(task: Task?) {
        require(task != null)
        if(task.state == RUNNING) {
            task.state = PAUSED
        }
    }
}
