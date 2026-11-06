package com.csh_s.core.execution

import com.csh_s.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.csh_s.core.iostream.task.Task

class ExecutionRepository() {
    internal val tasks = LinkedBlockingDeque<Task>()
}
