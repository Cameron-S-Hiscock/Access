package net.accesstechnologies.core

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.registry.*
import net.accesstechnologies.core.scheduler.*
import net.accesstechnologies.core.models.tasks.TaskFactory

object Controller {
    val id: String = Id.genId()

    fun initialize() {
        println("CONTROLLER : INITIALIZE : EXECUTION")
        shutdown()
    }

    fun shutdown() {
        println("CONTROLLER : SHUTDOWN : EXECUTION")
    }
}