package com.cameronsh.app

import com.cameronsh.utils.Id

import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.systems.SystemsBridge

import androidx.compose.ui.window.application
import androidx.compose.ui.window.*

import org.graalvm.polyglot.Context
import org.graalvm.polyglot.Source
import java.io.File

fun main(args: Array<String>) = application {
    println("${args.size}")
    for(arg in args) {println(arg)}

    val id: String = Id.genId()

    println("Kotlin")

    val result = SystemsBridge.systemsTask("Rust")
    println(result)

    Context.create("js").use { context ->
        context.eval(Source.newBuilder("js", File("web/js/src/lib.js")).build())
        val result = context.getBindings("js").getMember("weblog").execute("JavaScript")
    }

    with(Composer) { Compose() }
}
