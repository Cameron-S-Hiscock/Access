package com.cameronsh.ui

import com.cameronsh.utils.Id
import java.util.UUID

import java.util.concurrent.ConcurrentHashMap

class RequestLibrary() {
    val id: UUID = Id.genId(this)

    val requests = ConcurrentHashMap<String, () -> Unit>()

    fun getRequest(request: String?): UIResult {
        if(request == null) {
            return UIResult(error = RequestError.NullRequest(request))
        }
        val action = requests.get(request)
        if(action == null) {
            return UIResult(error = RequestError.UnknownRequest(request))
        }
        
        return UIResult(success = UISuccess.Value(action))
    }
}