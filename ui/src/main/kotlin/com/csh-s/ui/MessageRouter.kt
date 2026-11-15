package com.csh_s.ui

import com.csh_s.utils.Id
import java.util.UUID

import org.cef.browser.CefBrowser
import org.cef.browser.CefMessageRouter
import org.cef.handler.CefMessageRouterHandlerAdapter
import org.cef.callback.CefQueryCallback
import org.cef.browser.CefFrame
import com.csh_s.utils.AppError
import com.csh_s.utils.AppSuccess
import com.csh_s.utils.AppResult

class MessageRouter(
    appId: UUID,
    requestLibrary: RequestLibrary = RequestLibrary(),
) {
    val id: UUID = Id.genId(this)

    val accessRequestLibCodes: Set<Any?> = setOf(null, "access")
    
    val instance: CefMessageRouter = CefMessageRouter.create().apply {
        addHandler(object: CefMessageRouterHandlerAdapter() {
            override fun onQuery(
                browser: CefBrowser?,
                frame: CefFrame?,
                queryId: Long,
                cefRequest: String,
                presistent: Boolean,
                callback: CefQueryCallback?,
            ): Boolean {
                val fullRequest = cefRequest.lowercase()
                println("UI sent: $fullRequest")
    

                val splitRequest = fullRequest.split(":")
                val request: String? = splitRequest.elementAtOrNull(0)?.trim()
                val module: String? = splitRequest.elementAtOrNull(1)?.trim()

                var requestAction: UIResult? = null
                if(module in accessRequestLibCodes) {
                    requestAction = requestLibrary.getRequest(request)
                } else {

                }

                try {
                    val action = requestAction?.success as () -> Any?
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
