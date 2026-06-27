package net.accesstechnologies.core

import net.accesstechnologies.utils.Id

object Controller {
    fun initialize() {
        println("CONTROLLER : INITIALIZE : EXECUTION")
        val Id = Id.genId()
        shutdown()
    }

    fun shutdown() {
        println("CONTROLLER : SHUTDOWN : EXECUTION")
    }
}