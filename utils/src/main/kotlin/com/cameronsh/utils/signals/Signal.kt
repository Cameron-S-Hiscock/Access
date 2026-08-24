package com.cameronsh.utils.signals

import com.cameronsh.utils.Id
import java.util.UUID

class Signal(
    val origin: UUID,
    val destination: UUID,
    val code: SignalCode,
    val action: () -> Unit,
) {
    val id: UUID = Id.genId(this)
}
