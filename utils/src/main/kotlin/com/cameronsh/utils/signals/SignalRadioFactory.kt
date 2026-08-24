package com.cameronsh.utils.signals

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.utils.FreqTable

class SignalRadioFactory {
    val id: UUID = Id.genId(this)

    fun create(freq: Int): SignalRadio {
        val signalRadio = SignalRadio(freq)
        FreqTable.registerSignalRadio(signalRadio)
        return signalRadio
    }
}
