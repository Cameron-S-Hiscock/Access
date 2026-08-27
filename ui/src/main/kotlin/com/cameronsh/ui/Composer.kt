package com.cameronsh.ui

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.data.DataFactory
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.ProcessWorker
import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.task.Task
import com.cameronsh.ui.AssetServer
import me.friwi.jcefmaven.CefAppBuilder
import org.cef.CefApp
import org.cef.CefClient
import org.cef.browser.CefBrowser
import org.cef.browser.CefMessageRouter
import org.cef.handler.CefMessageRouterHandlerAdapter
import org.cef.callback.CefQueryCallback
import org.cef.browser.CefFrame
import kotlin.system.exitProcess
import com.cameronsh.ui.MessageRouter

object Composer {
    val id: UUID = Id.genId(this)

    val dataFactory = DataFactory()
    val taskFactory = TaskFactory(
        dataFactory =  dataFactory,
    )
    val UIProcessWorker = ProcessWorker(
        name = "UIProcessWorker",
        host = id,
    )

    lateinit var assetServer: AssetServer
    lateinit var cefApp: CefApp
    lateinit var cefClient: CefClient
    lateinit var browser: CefBrowser
    lateinit var messageRouter: CefMessageRouter

    fun init() {
        assetServer = AssetServer()
        assetServer.start()
        println("AssetServer listening on port ${assetServer.port}")

        val url = "https://localhost:${assetServer.port}"

        val builder = CefAppBuilder()
        builder.setInstallDir(java.io.File("jcef-bundle"))
        builder.cefSettings.windowless_rendering_enabled = false
        builder.cefSettings.locale = "en-US"
        builder.cefSettings.let { }
        builder.addJcefArgs("--ignore-certificate-errors")

        cefApp = builder.build()
        cefClient = cefApp.createClient()

        println("Loading browser URL: $url")
        browser = cefClient.createBrowser(
            url,
            false,
            false,
        )
        browser.getDevTools(null)

        cefClient.addMessageRouter(MessageRouter.instance)
    }

    fun shutdown() {
        cefClient.dispose()
        cefApp.dispose()
    }
}
