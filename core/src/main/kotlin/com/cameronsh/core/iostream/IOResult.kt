package com.cameronsh.core.iostream

import com.cameronsh.core.iostream.IOSuccess
import com.cameronsh.core.iostream.IOError

class IOResult(
    val success: IOSuccess? = null,
    val error: IOError? = null,
) {
    
}