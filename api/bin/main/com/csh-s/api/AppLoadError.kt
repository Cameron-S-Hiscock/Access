package com.csh_s.api

sealed class AppLoadError : APIError {
    data class InvalidIndexHTMLFile(val filePath: String) : AppLoadError()
    data class InvalidIndexBackFile(val filePath: String) : AppLoadError()
    data class InvalidFile(val filePath: String) : AppLoadError()
    data class FailedCompliation(val error: String) : AppLoadError()
}