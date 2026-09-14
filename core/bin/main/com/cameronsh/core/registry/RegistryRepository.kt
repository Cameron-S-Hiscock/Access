package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.task.Task

class RegistryRepository() {
    internal val tasks = LinkedBlockingDeque<Task>()
}
