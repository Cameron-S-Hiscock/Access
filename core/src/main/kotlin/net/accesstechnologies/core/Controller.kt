package net.accesstechnologies.core

import net.accesstechnologies.utils.Id

object Controller {
    val id: Int = Id.genId()

    fun initialize() {
        println("CONTROLLER : INITIALIZE : EXECUTION")
        shutdown()
    }

    fun shutdown() {
        println("CONTROLLER : SHUTDOWN : EXECUTION")
    }
}