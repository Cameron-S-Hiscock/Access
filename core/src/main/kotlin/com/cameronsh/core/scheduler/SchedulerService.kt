package com.cameronsh.core.scheduler

import com.cameronsh.utils.Id

import com.cameronsh.core.scheduler.SchedulerRepository

object SchedulerService {
    private val SchedulerRepositroy: SchedulerRepository = SchedulerRepository()
    val id: String = Id.genId()
}
