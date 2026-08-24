package com.cameronsh.app

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.systems.SystemsBridge

import androidx.compose.ui.window.application
import androidx.compose.ui.window.*
import androidx.compose.runtime.Composable
import kotlinx.coroutines.*
import java.io.File

fun main(args: Array<String>) = application {
    println("Main Thread: ${Thread.currentThread().name}")

    println("Kotlin")

    println(SystemsBridge.systems_log("Rust"))

    with(Composer) { Compose() }
}
