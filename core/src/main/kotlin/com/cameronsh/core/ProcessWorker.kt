package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.registry.RegistryWorker
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.schedule.ScheduleWorker
import com.cameronsh.core.execution.ExecutionWorker
import com.cameronsh.core.iostream.data.DataFactory
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskPriority
import com.cameronsh.core.iostream.task.TaskPriority.*
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicBoolean

class ProcessWorker(
    val name: String = "ProcessWorker",
    val host: UUID,
) : Worker(name = name) {
    private val RSETasks = LinkedBlockingDeque<Task>()

    private val taskFactory = TaskFactory()
    private val registryWorker = RegistryWorker(
        RSETasks = RSETasks,
        taskFactory = taskFactory,
    )
    private val scheduleWorker = ScheduleWorker(
        RSETasks = RSETasks,
        taskFactory = taskFactory,
    )
    private val executionWorker = ExecutionWorker(
        RSETasks = RSETasks,
        scheduleWorker = scheduleWorker,
        taskFactory = taskFactory,
    )

    private val localactions = LinkedBlockingDeque<() -> Unit>()
    private val localRunning = AtomicBoolean(true)
    private val localThread = Thread.ofVirtual().name("${name}Local").unstarted {
        while(localRunning.get()) {
            val action = localactions.take()
            runCatching { action() }.onFailure { println("Local task failed: ${it.message}") }
        }
    }

    fun run() {
        registryWorker.start()
        scheduleWorker.start()
        executionWorker.start()
    }

    fun submitTask(task: Task) {
        registryWorker.addWork(
            taskFactory.create(name = "${name}RegisterTask") { registryWorker.registerTask(task) }
        )
        scheduleWorker.addWork(
            taskFactory.create(name = "${name}ScheduleTask") { scheduleWorker.scheduleTask(task) }
        )
        executionWorker.addWork(
            taskFactory.create(name = "${name}ExecuteTask") { executionWorker.executeTask(task) }
        )
    }

    fun createTask(
        name: String = "CreateTask",
        priority: TaskPriority = NORMAL,
        action: () -> Unit,
    ) {
        taskFactory.create(name = name, priority = priority) { action }
    }
}
