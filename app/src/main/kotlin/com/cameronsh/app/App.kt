package com.cameronsh.app

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.systems.SystemsBridge
import com.cameronsh.web.WebBridge

import androidx.compose.ui.window.application
import androidx.compose.ui.window.*

import java.io.File

fun main(args: Array<String>) = application {
    println("${args.size}")
    for(arg in args) {println(arg)}

    println("Kotlin")

    println(SystemsBridge.systems_log("Rust"))

    println(WebBridge.runNodeScript("main.js", "JavaScript"))

    with(Composer) { Compose() }
}
