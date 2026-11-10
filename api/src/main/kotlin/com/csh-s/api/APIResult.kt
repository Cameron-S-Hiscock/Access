package com.csh_s.api

import java.util.UUID
import com.csh_s.utils.AppResult
import com.csh_s.utils.AppSuccess
import com.csh_s.utils.AppError

class APIResult(
    override val success: AppSuccess? = null,
    override val error: AppError? = null,
) : AppResult {
    
}
