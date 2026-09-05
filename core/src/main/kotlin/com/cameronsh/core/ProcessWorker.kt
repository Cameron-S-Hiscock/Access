package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.registry.RegistryWorker
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.schedule.ScheduleWorker
import com.cameronsh.core.execution.ExecutionWorker
import com.cameronsh.core.iostream.data.DataFactory
import com.cameronsh.core.iostream.message.MessageFactory
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

    val taskFactory = TaskFactory()
    val messageFactory = MessageFactory()
    private val executionWorker = ExecutionWorker(
        RSETasks = RSETasks,
        taskFactory = taskFactory,
    )
    private val scheduleWorker = ScheduleWorker(
        RSETasks = RSETasks,
        taskFactory = taskFactory,
        executionWorker = executionWorker,
    )
    private val registryWorker = RegistryWorker(
        RSETasks = RSETasks,
        taskFactory = taskFactory,
        scheduleWorker = scheduleWorker,
    )

    private val localActions = LinkedBlockingDeque<() -> Unit>()
    private val localRunning = AtomicBoolean(true)
    private val localThread = Thread.ofVirtual().name("${name}Local").unstarted {
        while(localRunning.get()) {
            val action = localActions.take()
            runCatching { action() }.onFailure { println("Local task failed: ${it.message}") }
        }
    }

    fun run() {
        registryWorker.start()
        scheduleWorker.start()
        executionWorker.start()
        localThread.start()
    }

    fun submitWork(task: Task) {
        registryWorker.addWork(
            taskFactory.create(name = "${task.name}RegisterTask") { registryWorker.registerTask(task) }
        )
    }

    fun submitLocal(action: () -> Unit) {
        localActions.add(action)
    }
}
