package com.cameronsh.core.schedule

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.task.Task

class ScheduleRepository() {
    internal val tasks = LinkedBlockingDeque<Task>()
}
