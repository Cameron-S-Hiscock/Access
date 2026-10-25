package com.cameronsh.ui

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.ConcurrentHashMap
import com.cameronsh.utils.AppSuccess
import kotlin.system.exitProcess

class RequestLibrary() {
    val id: UUID = Id.genId(this)

    val requests = ConcurrentHashMap<String, (List<String>) -> Any?>()

    fun getRequest(request: String?, args: List<String> = emptyList()): UIResult {
        if(request == null) {
            return UIResult(error = RequestError.NullRequest(request))
        }
        val action = requests[request]?: return UIResult(error = RequestError.UnknownRequest(request))
        
        return UIResult(success = AppSuccess.Value(action(args)))
    }

    init {
        requests["exit-app"] = { _ -> exitProcess(0) }
        requests["restart-app"] = { _ -> /* restart */}
        requests["load-app"] = { args ->
            val appPath = args.firstOrNull()
            // load app
        }
        requests["edit-ui"] = { args ->
            // edit ui
        }
    }
}