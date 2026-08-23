package com.cameronsh.core.schedule

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.schedule.ScheduleService

class ScheduleServiceFactory {
    fun create(): ScheduleService {
        val scheduleService = ScheduleService()
        return scheduleService
    }
}
