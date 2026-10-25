package com.cameronsh.ui

import com.cameronsh.utils.AppError
import com.cameronsh.utils.AppSuccess
import com.cameronsh.utils.AppResult

class UIResult(
    override val success: AppSuccess? = null,
    override val error: AppError? = null,
) : AppResult {
    
}