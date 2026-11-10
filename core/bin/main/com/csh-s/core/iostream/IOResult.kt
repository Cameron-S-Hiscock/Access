package com.csh_s.core.iostream

import com.csh_s.utils.AppError
import com.csh_s.utils.AppSuccess
import com.csh_s.utils.AppResult

class IOResult(
    override val success: AppSuccess? = null,
    override val error: AppError? = null,
) : AppResult {
    
}