package com.csh_s.ui

sealed class RequestError : UIError {
    data class UnknownRequest(val request: String) : RequestError()
    data class ErrorProneAction(val request: String) : RequestError()
    data class NullRequest(val request: String?) : RequestError()
}