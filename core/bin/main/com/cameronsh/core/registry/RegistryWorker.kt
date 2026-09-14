package com.cameronsh.core.registry

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.iostream.task.TaskState.*
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.schedule.ScheduleWorker
import java.util.concurrent.LinkedBlockingDeque

class RegistryWorker(
    val name: String = "RegistryWorker",
    private val RSETasks: LinkedBlockingDeque<Task>,
    private val taskFactory: TaskFactory,
    private val scheduleWorker: ScheduleWorker,
) : Worker(name = name) {
    private val registryService = RegistryService(RSETasks = RSETasks)

    fun registerTask(task: Task) {
        registryService.registerTask(task)
        scheduleWorker.scheduleTask(task)
    }
}
