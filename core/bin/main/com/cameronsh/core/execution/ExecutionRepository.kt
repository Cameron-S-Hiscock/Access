package com.cameronsh.core.execution

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.task.Task

class ExecutionRepository() {
    internal val tasks = LinkedBlockingDeque<Task>()
}
