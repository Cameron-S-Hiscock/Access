package com.csh_s.ui

import com.csh_s.utils.AppError
import com.csh_s.utils.AppSuccess
import com.csh_s.utils.AppResult

class UIResult(
    override val success: AppSuccess? = null,
    override val error: AppError? = null,
) : AppResult {
    
}