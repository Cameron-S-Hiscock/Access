package com.csh_s.core

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.core.iostream.task.Task
import com.csh_s.core.schedule.ScheduleWorker

class RSEQueue() : Iterator<Task> {
    val id: UUID = Id.genId(this)

    private val tasks: MutableList<Task> = mutableListOf()

    override fun hasNext(): Boolean = tasks.isNotEmpty()

    override fun next(): Task {
        if (!hasNext()) throw NoSuchElementException()
        return tasks.removeAt(0)
    }

    suspend fun submitTask(task: Task) {

    }

    suspend fun getTask(critical: Boolean = false): Task? {
        return null
    }

    suspend fun moveTask(scheduleWorker: ScheduleWorker, aIdx: Int = 0, bIdx: Int = 0, critical: Boolean = false) {
        
    }
}
