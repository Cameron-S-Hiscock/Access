package com.cameronsh.core.iostream.task

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.*
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.data.DataFactory

class TaskFactory(
    private val registryService: RegistryService,
    private val dataFactory: DataFactory,
) {
    fun create(
        name: String,
        priority: TaskPriority,
        action: () -> Unit
    ): Task {
        val task = Task(
            name = name,
            priority = priority,
            dataFactory = dataFactory,
            action = action
        )
        println("Created task: ${name}Task")
        registryService.registerTask(task)
        return task
    }
}
