package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.registry.RegistryService
import com.cameronsh.core.scheduler.SchedulerService
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.launcher.Launcher
import com.cameronsh.core.launcher.LauncherFactory
import com.cameronsh.core.iostream.IOStreamFactory

object Controller {
    val id: UUID = Id.genId(this)

    val launchers = arrayListOf<Launcher>()
    val criticalLauncher = Launcher(SchedulerService)

    val registrySchedulerIOStream = IOStreamFactory.create(
        originId = RegistryService.id,
        destinationId = SchedulerService.id,
    )

    init {
        repeat(2) {
            launchers.add(LauncherFactory.create())
        }
    }
}

