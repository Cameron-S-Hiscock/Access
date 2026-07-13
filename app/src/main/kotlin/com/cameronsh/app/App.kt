package com.cameronsh.app

import com.cameronsh.utils.Id

import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer

import androidx.compose.ui.window.application
import androidx.compose.ui.window.*

fun main(args: Array<String>) = application {
    println("${args.size}")
    for(arg in args) {println(arg)}

    val id: String = Id.genId()

    with(Composer) { Compose() }
}
