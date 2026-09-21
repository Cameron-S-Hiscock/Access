package com.cameronsh.app

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.api.UICoreBridge
import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.ui.MainWindow
import com.cameronsh.systems.SystemsBridge
import com.cameronsh.systems.NetworkBridge
import javax.swing.SwingUtilities
import java.io.File
import kotlinx.coroutines.*

suspend fun main(args: Array<String>) {
    val AppNetworkServer = NetworkBridge.newServer("[::1]")

    withContext(Dispatchers.Default) {
    this.launch { println("Main Thread: ${Thread.currentThread().name}") }

    this.launch {
        UICoreBridge.init()
        Controller.init()
        Composer.init()
    }

    this.launch {
        Composer.initUI()
        SwingUtilities.invokeLater {
            val window = MainWindow(Composer)
            window.isVisible = true
        }
    }
    }
}
