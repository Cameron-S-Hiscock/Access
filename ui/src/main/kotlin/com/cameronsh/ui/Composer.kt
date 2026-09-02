package com.cameronsh.ui

import com.cameronsh.core.BridgeRepository
import com.cameronsh.utils.Id
import java.util.UUID

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
import org.cef.handler.CefLoadHandler
import org.cef.handler.CefLoadHandlerAdapter
import com.cameronsh.core.iostream.message.Message

object Composer {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("Composer", id) }

    val UIProcessWorker = ProcessWorker(
        name = "UIProcessWorker",
        host = id,
    )
    init {
        UIProcessWorker.start()
        UIProcessWorker.taskFactory.create(name = "${UIProcessWorker.name}StartWorkers") { UIProcessWorker.run() }
        UIProcessWorker.addWork(
            UIProcessWorker.taskFactory.create(name = "UICoreBridgeUITest") {
                val IO = BridgeRepository.iostreams["UICoreBridge"]
                require(IO != null)
                IO.send(
                    author = id,
                    message = UIProcessWorker.messageFactory.create(
                        name = "UICoreBridgeUITestMessage",
                        origin = id,
                        destination = IO.id,
                        task = UIProcessWorker.taskFactory.create(name = "UICoreBridgeUITestMessageTask") { println("UICoreBridgeUITestMessageArrived") },
                    )
                )
            }
        )
    }

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
        builder.cefSettings.remote_debugging_port = 9222
        builder.addJcefArgs("--ignore-certificate-errors")
        builder.addJcefArgs("--remote-allow-origins=http://localhost:9222")

        cefApp = builder.build()
        cefClient = cefApp.createClient()

        println("Loading browser URL: $url")
        browser = cefClient.createBrowser(
            url,
            false,
            false,
        )

        cefClient.addMessageRouter(MessageRouter.instance)
    }

    fun shutdown() {
        cefClient.dispose()
        cefApp.dispose()
    }
}
