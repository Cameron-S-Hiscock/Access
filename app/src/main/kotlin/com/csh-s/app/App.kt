package com.csh_s.app

import com.csh_s.utils.Id
import java.util.UUID

import com.csh_s.api.UICoreBridge
import com.csh_s.core.Controller
import com.csh_s.ui.Composer
import com.csh_s.ui.MainWindow
import com.csh_s.systems.SystemsBridge
import com.csh_s.systems.NetworkBridge
import javax.swing.SwingUtilities
import java.io.File
import kotlinx.coroutines.*

suspend fun main(args: Array<String>) {
    val appNetworkServer = NetworkBridge.newServer("[::1]")

    println("Main Thread: ${Thread.currentThread().name}")

    UICoreBridge.init()
    Controller.init()
    Composer.init()

    Composer.initUI()
    SwingUtilities.invokeLater {
        val window = MainWindow(Composer)
        window.isVisible = true
    }
}
