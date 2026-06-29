package net.accesstechnologies.core.scheduler

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.scheduler.SchedulerRepository

object SchedulerService {
    private val SchedulerRepositroy: SchedulerRepository = SchedulerRepository()
    val id: String = Id.genId()
}