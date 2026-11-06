package com.csh_s.core

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.workers.Worker
import com.csh_s.core.registry.RegistryWorker
import com.csh_s.core.iostream.task.TaskFactory
import com.csh_s.core.schedule.ScheduleWorker
import com.csh_s.core.execution.ExecutionWorker
import com.csh_s.core.iostream.data.DataFactory
import com.csh_s.core.iostream.message.Message
import com.csh_s.core.iostream.message.MessageFactory
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.task.TaskPriority
import com.csh_s.core.iostream.task.TaskPriority.*
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.CopyOnWriteArrayList
import com.csh_s.core.RSEQueue

class ProcessWorker(
    val name: String = "ProcessWorker",
    val host: UUID,
) : Worker(name = name) {
    private val rseQueue = RSEQueue()

    val taskFactory = TaskFactory()
    val messageFactory = MessageFactory()
    private val executionWorker = ExecutionWorker(
        rseQueue = rseQueue,
        taskFactory = taskFactory,
    )
    private val scheduleWorker = ScheduleWorker(
        rseQueue = rseQueue,
        taskFactory = taskFactory,
    )
    private val registryWorker = RegistryWorker(
        rseQueue = rseQueue,
        taskFactory = taskFactory,
    )

    private val localActions = LinkedBlockingDeque<() -> Any?>()
    private val localRunning = AtomicBoolean(true)
    private val localThread = Thread.ofVirtual().name("${name}Local").unstarted {
        while(localRunning.get()) {
            val action = localActions.take()
            runCatching { action() }.onFailure { println("Local task failed: ${it.message}") }
        }
    }

    suspend fun run() {
        registryWorker.start()
        scheduleWorker.start()
        executionWorker.start()
        localThread.start()
        registryWorker.registerRSETasks()
        scheduleWorker.scheduleRSETasks()
        executionWorker.executeRSETasks()
        start()
    }

    suspend fun submitWork(task: Task) {
        if(task.priority == CRITICAL) {
            registryWorker.addWork(
                taskFactory.create(name = "${task.name}RegisterCriticalTask") { registryWorker.registerTask(task) },
                true,
            )
        } else {
            registryWorker.addWork(
                taskFactory.create(name = "${task.name}RegisterTask") { registryWorker.registerTask(task) }
            )
        }
    }

    suspend fun submitLocal(action: () -> Any?) {
        localActions.add(action)
    }
}
