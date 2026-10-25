package com.cameronsh.core.iostream

import com.cameronsh.utils.AppError
import com.cameronsh.utils.AppSuccess
import com.cameronsh.utils.AppResult

class IOResult(
    override val success: AppSuccess? = null,
    override val error: AppError? = null,
) : AppResult {
    
}