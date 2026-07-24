package com.cameronsh.core.launcher

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.launcher.Launcher
import com.cameronsh.core.scheduler.SchedulerService
import com.cameronsh.core.Controller

object LauncherFactory {
    public val id: UUID = Id.genId()

    fun create(): Launcher {
        val launcher = Launcher(SchedulerService)
        return launcher
    }
}
