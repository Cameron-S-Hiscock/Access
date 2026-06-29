package net.accesstechnologies.core.scheduler

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.scheduler.SchedulerRepository

class SchedulerService(
    val SchedulerRepositroy: SchedulerRepository
) {
    val id: String = Id.genId()
}