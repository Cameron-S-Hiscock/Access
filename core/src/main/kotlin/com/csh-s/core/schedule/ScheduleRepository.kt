package com.csh_s.core.schedule

import com.csh_s.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.csh_s.core.iostream.task.Task

class ScheduleRepository() {
    internal val tasks = LinkedBlockingDeque<Task>()
}
