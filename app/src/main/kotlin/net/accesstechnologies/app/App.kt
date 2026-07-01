package net.accesstechnologies.app

import net.accesstechnologies.utils.Id

import net.accesstechnologies.core.Controller
import net.accesstechnologies.ui.Composer

import androidx.compose.ui.window.application
import androidx.compose.ui.window.*

fun main(args: Array<String>) = application {
    val argc: Int = args.size
    println("${argc}")
    for(arg in args) {println(arg)}

    val id: String = Id.genId()

    with(Composer) { Compose() }
}
