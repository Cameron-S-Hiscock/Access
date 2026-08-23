package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.schedule.ScheduleService
import com.cameronsh.core.iostream.task.TaskFactory

object Controller {
    val id: UUID = Id.genId(this)
}
