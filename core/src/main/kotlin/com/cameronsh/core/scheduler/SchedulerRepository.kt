package com.cameronsh.core.scheduler

import com.cameronsh.utils.Id

import com.cameronsh.core.models.tasks.Task

class SchedulerRepository(
    val taskCap: Int = 100
) {
    val id: String = Id.genId()
    val tasks: Array<Task?> = arrayOfNulls(taskCap)
    val critical: Array<Task?> = arrayOfNulls(10)
}
