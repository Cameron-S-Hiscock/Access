package com.cameronsh.core.scheduler

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.Task

class SchedulerRepository(
    val taskCap: Int = 100
) {
    init { Id.genId(this) }
    val tasks: Array<Task?> = arrayOfNulls(taskCap)
}
