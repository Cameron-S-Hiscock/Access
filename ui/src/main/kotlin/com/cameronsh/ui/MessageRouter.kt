package com.cameronsh.ui

import com.cameronsh.utils.Id
import java.util.UUID

import kotlin.system.exitProcess
import org.cef.browser.CefBrowser
import org.cef.browser.CefMessageRouter
import org.cef.handler.CefMessageRouterHandlerAdapter
import org.cef.callback.CefQueryCallback
import org.cef.browser.CefFrame
import com.cameronsh.ui.Composer

object MessageRouter {
    val id: UUID = Id.genId(this)

    val instance: CefMessageRouter = CefMessageRouter.create().apply {
        addHandler(object: CefMessageRouterHandlerAdapter() {
            override fun onQuery(
                browser: CefBrowser?,
                frame: CefFrame?,
                queryId: Long,
                request: String?,
                presistent: Boolean,
                callback: CefQueryCallback?,
            ): Boolean {
                println("JS sent: $request")
    
                // TODO: Route to Controller via IOStream
    
                when(request) {
                    "exit-app" -> {
                        callback?.success("")
                        exitProcess(0)
                    }
                    "restart-app" -> {
                        callback?.success("")
                        println("Restarting Access")
                    }
                }

                callback?.success("Kotlin received: $request")
                return true
            }
        }, true)
    }
}
