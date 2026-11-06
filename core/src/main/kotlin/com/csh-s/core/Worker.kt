package com.csh_s.core.workers

import com.csh_s.utils.Id
import java.util.UUID

import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.LinkedBlockingDeque
import java.lang.Thread
import com.csh_s.core.iostream.task.Task
import com.csh_s.core.iostream.task.TaskPriority.*
import kotlin.random.Random
import kotlinx.coroutines.*

open class Worker(
    name: String = "Worker",
) {
    val id: UUID = Id.genId(this)

    private val running = AtomicBoolean(true)

    private val executor = Executors.newFixedThreadPool(4, Thread.ofVirtual().name(name).factory())
    private val dispatcher = executor.asCoroutineDispatcher()
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    suspend fun addWork(task: Task, critical: Boolean = false) {
        scope.launch {
            try {
                task.action()
            } catch(e: Exception) {
                println("Task ${task.name} failed: ${e.message}")
            }
        }
    }

    fun start() {}
    fun join() {}
    fun stop() {
        running.set(false)
        scope.cancel()
        dispatcher.close()
    }
}
