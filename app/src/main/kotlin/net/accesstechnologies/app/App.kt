package net.accesstechnologies.app

import net.accesstechnologies.core.Controller
import net.accesstechnologies.ui.Composer
import androidx.compose.ui.window.application

import androidx.compose.ui.window.*

fun main(args: Array<String>) = application {
    println("MAIN : EXECUTION")
    val argc: Int = args.size
    println("${argc}")
    for(arg in args) {println(arg)}
    Controller.initialize()
    with(Composer) { Compose() }
    println("MAIN : SHUTDOWN")
}