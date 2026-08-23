package com.cameronsh.core.workers

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.*
import java.lang.Thread
import com.cameronsh.core.iostream.task.Task

open class Worker(
    name: String = "Worker",
) {
    val id: UUID = Id.genId(this)

    private val tasks = LinkedBlockingQueue<Task>()
    val thread = Thread.ofVirtual().name(name).unstarted() {
        while(true) {
            val task = tasks.take()
            task()
        }
    }
    fun addWork(task: Task) = tasks.add(task)

    fun start() = thread.start()
    fun join() = thread.join()
}
