package com.csh_s.utils

interface AppResult {
    val success: AppSuccess?
    val error: AppError?
}