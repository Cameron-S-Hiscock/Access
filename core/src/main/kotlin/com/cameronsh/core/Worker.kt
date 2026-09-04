package com.cameronsh.core.workers

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.LinkedBlockingDeque
import java.lang.Thread
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.core.iostream.task.TaskPriority.*

open class Worker(
    name: String = "Worker",
) {
    val id: UUID = Id.genId(this)

    private val running = AtomicBoolean(true)

    private val tasks = LinkedBlockingDeque<Task>()
    val thread = Thread.ofVirtual().name(name).unstarted() {
        while(running.get()) {
            val task = tasks.take()
            runCatching { task.action() }.onFailure { println("Task ${task.name} failed: ${it.message}") }
        }
    }
    fun addWork(task: Task) = tasks.add(task)

    fun addCriticalWork(task: Task) {
        try {
            require(task.priority == CRITICAL)
        } catch(e: Exception) {
            println("Invalid: ${e}")
            return
        }
        tasks.addFirst(task)
    }

    fun start() = thread.start()
    fun join() = thread.join()
    fun stop() {
        running.set(false)
        thread.interrupt()
    }
}
