package com.cameronsh.ui

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.ConcurrentHashMap
import com.cameronsh.utils.AppSuccess

class RequestLibrary() {
    val id: UUID = Id.genId(this)

    val requests = ConcurrentHashMap<String, () -> Any?>()

    fun getRequest(request: String?): UIResult {
        if(request == null) {
            return UIResult(error = RequestError.NullRequest(request))
        }
        val action = requests.get(request)
        if(action == null) {
            return UIResult(error = RequestError.UnknownRequest(request))
        }
        
        return UIResult(success = AppSuccess.Value(action))
    }
}