package com.csh_s.api

import java.util.UUID

sealed class AppLoadError : APIError {
    data class InvalidIndexHTMLFile(val filePath: String) : AppLoadError()
    data class InvalidIndexJVMFile(val filePath: String) : AppLoadError()
    data class InvalidFile(val filePath: String) : AppLoadError()
    data class FailedCompliation(val error: String) : AppLoadError()
    data class AlreadyLoadedApp(val id: UUID) : AppLoadError()
}
