package net.accesstechnologies.app

import net.accesstechnologies.core.Controller
import net.accesstechnologies.ui.Composer

import androidx.compose.ui.window.*

fun main(args: Array<String>) = application {
    println("MAIN : EXECUTION")
    for(arg in args) {println(arg)}
    Controller.initialize()
    Composer()
    println("MAIN : SHUTDOWN")
}