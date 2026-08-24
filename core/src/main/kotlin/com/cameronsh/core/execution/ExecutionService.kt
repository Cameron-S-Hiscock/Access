package com.cameronsh.core.execution

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.schedule.ScheduleWorker
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskState.*
import java.util.concurrent.LinkedBlockingDeque

class ExecutionService(
    private val RSETasks: LinkedBlockingDeque<Task>,
) {
    private val executionRepository = ExecutionRepository()
    val id: UUID = Id.genId(this)

    fun executeTask(task: Task) {
        if(task.state == SCHEDULED || task.state == PAUSED) {
            task.state = RUNNING
            println("Executing task: ${task.name}")
            task.action()
        }
        task.action()
    }

    fun pauseTask(task: Task?) {
        require(task != null)
        if(task.state == RUNNING) {
            task.state = PAUSED
        }
    }
}
