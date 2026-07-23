package com.cameronsh.core.scheduler

import com.cameronsh.utils.Id

import com.cameronsh.core.models.task.Task

class SchedulerRepository(
    val taskCap: Int = 100
) {
    val id: String = Id.genId()
    val tasks: Array<Task?> = arrayOfNulls(taskCap)
}
