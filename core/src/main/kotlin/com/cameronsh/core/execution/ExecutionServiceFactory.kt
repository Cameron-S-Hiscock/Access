package com.cameronsh.core.execution

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.execution.ExecutionService
import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.Controller

class ExecutionServiceFactory(private val scheduleService: ScheduleService) {
    fun create(): ExecutionService {
        val executionService = ExecutionService(scheduleService)
        return executionService
    }
}
