package org.access.ui

import org.access.utils.Id

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