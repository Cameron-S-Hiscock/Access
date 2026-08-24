package com.cameronsh.utils.signals

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.utils.signals.SignalCode.*
import com.cameronsh.utils.FreqTable
import com.google.common.collect.Maps
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap

class SignalRadio(var freq: Int) {
    val id: UUID = Id.genId(this)

    fun listen(freq: Int): Signal? {
        return FreqTable.broadcasts[freq]
    }

    fun broadcast(freq: Int, signal: Signal) {
        FreqTable.broadcasts.putIfAbsent(freq, signal)
    }

    fun tuneFrequency(tuneFreq: Int) {
        freq = tuneFreq
    }
}
