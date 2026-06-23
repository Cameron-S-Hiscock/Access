package net.accesstechnologies.core

import net.accesstechnologies.utils.Id

object Controller {
    fun start() {
        println("CONTROLLER : START : EXECUTION")
        val Id = Id.genId()
        loop()
    }

    fun loop() {
        println("CONTROLLER : LOOP : EXECUTION")
        end()
    }

    fun end() {
        println("CONTROLLER : END : EXECUTION")
    }
}