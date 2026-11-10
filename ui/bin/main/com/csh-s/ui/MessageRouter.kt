package com.csh_s.ui

import com.csh_s.utils.Id
import java.util.UUID

import kotlin.system.exitProcess
import org.cef.browser.CefBrowser
import org.cef.browser.CefMessageRouter
import org.cef.handler.CefMessageRouterHandlerAdapter
import org.cef.callback.CefQueryCallback
import org.cef.browser.CefFrame
import com.csh_s.ui.Composer
import com.csh_s.ui.RequestLibrary
import com.csh_s.utils.AppError
import com.csh_s.utils.AppSuccess
import com.csh_s.utils.AppResult

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
                try {
                    val action = requestAction.success as () -> Any?
                    action()
                } catch(e: Exception) {
                    println("${e}")
                }

                callback?.success("Kotlin received: $request")
                return true
            }
        }, true)
    }
}
