package com.cameronsh.core.workers

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.workers.Worker
import com.cameronsh.core.registry.RegistryWorker
import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.schedule.ScheduleWorker
import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.execution.ExecutionWorker
import com.cameronsh.core.execution.ExecutionService
import com.cameronsh.core.iostream.data.DataFactory

class ProcessWorker(
    name: String = "ProcessWorker",
    host: UUID,
) : Worker(name = name) {
    val registryService = RegistryService()
    val scheduleService = ScheduleService()
    val executionService = ExecutionService(scheduleService = scheduleService)
    val dataFactory = DataFactory()
    val taskFactory = TaskFactory(
        registryService = registryService,
        dataFactory = dataFactory,
    )
    val scheduleWorker = ScheduleWorker(
        registryWorker = registryWorker
        taskFactory = taskFactory,
    )
    val executionWorker = ExecutionWorker(
        scheduleService = scheduleService,
        taskFactory = taskFactory,
    )
    val registryWorker = RegistryWorker(
        taskFactory = taskFactory,
    )
}
