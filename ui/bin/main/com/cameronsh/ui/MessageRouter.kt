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
import com.cameronsh.ui.RequestLibrary

class MessageRouter() {
    val id: UUID = Id.genId(this)

    val requestLibrary = RequestLibrary()
    
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
                println("UI sent: $request")
    
                val requestAction = requestLibrary.getRequest(request)
                if(requestAction.success is UISuccess.Value) {
                    val action = requestAction.success.value as () -> Unit
                    action()
                }

                callback?.success("Kotlin received: $request")
                return true
            }
        }, true)
    }
}
