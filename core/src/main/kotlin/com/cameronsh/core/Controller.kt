package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.registry.*
import com.cameronsh.core.scheduler.*
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.launcher.Launcher
import com.cameronsh.core.launcher.LauncherFactory

object Controller {
    val id: UUID = Id.genId()

    val launchers = arrayListOf<Launcher>()
    val criticalLauncher = Launcher(SchedulerService)

    init {
        repeat(2) {
            launchers.add(LauncherFactory.create())
        }
    }
}

