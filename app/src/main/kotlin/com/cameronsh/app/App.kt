package com.cameronsh.app

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.ui.MainWindow
import com.cameronsh.systems.SystemsBridge

import javax.swing.SwingUtilities

import java.io.File

fun main(args: Array<String>) {
    println("Main Thread: ${Thread.currentThread().name}")

    Controller.initMainProcess()

    Composer.init()
    SwingUtilities.invokeLater {
        val window = MainWindow(Composer)
        window.isVisible = true
    }
}
