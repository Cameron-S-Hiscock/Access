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

    val launcher = Launcher()
    val criticalLauncher = Launcher()

    val registrySchedulerIOStream = IOStreamFactory.create(
        originId = RegistryService.id,
        destinationId = SchedulerService.id,
    )

    suspend fun execute() {
        while(!SchedulerService.getSchedule().isNullOrEmpty()) {
            println("Task in schedule")
            for(task in SchedulerService.getSchedule()) {
                println("${task.name}")
            }
            val idx = SchedulerService.getSchedule().indexOfFirst { it != null }
            if(idx != -1) {
                launcher.executeTask(SchedulerService.getSchedule()[idx])
                println("${SchedulerService.getSchedule()[idx].name} removed from schedule")
                SchedulerService.getSchedule().removeAt(idx)
            }
        }
    }

    suspend fun pushUI() {
        println("Updating UI")
    }
}
