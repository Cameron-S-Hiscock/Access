package org.access.app

import org.access.core.Controller
import org.access.ui.createWindow

import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.window.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

fun main(args: Array<String>) = application {
    println("MAIN : EXECUTION")
    for(arg in args) {println(arg)}
    createWindow()
    Controller.start()
    println("MAIN : SHUTDOWN")
}