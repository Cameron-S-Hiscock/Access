package com.cameronsh.utils

interface AppResult {
    val success: AppSuccess?
    val error: AppError?
}