package com.cameronsh.core.execution

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.iostream.task.*
import com.cameronsh.core.iostream.task.TaskState.*

class ExecutionService(scheduleService: ScheduleService) {
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
