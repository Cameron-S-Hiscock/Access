package com.cameronsh.utils

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.utils.signals.Signal
import com.cameronsh.utils.signals.SignalRadio
import java.util.concurrent.*
import com.google.common.collect.Maps
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap

object FreqTable {
    val id: UUID = Id.genId(this)

    private val freqs = Maps.synchronizedBiMap(HashBiMap.create<Int, MutableList<SignalRadio>>())
    val broadcasts = Maps.synchronizedBiMap(HashBiMap.create<Int, Signal>())

    fun getSignalRadioBand(freq: Int): List<SignalRadio> {
        val radioBand = freqs[freq]
        require(radioBand != null)
        return radioBand
    }

    fun getSignalRadio(freq: Int, idx: Int): SignalRadio {
        val radioBand: List<SignalRadio> = getSignalRadioBand(freq)
        val radio: SignalRadio? = radioBand[idx]
        require(radio != null)
        return radio
    }

    fun registerSignalRadio(signalRadio: SignalRadio) {
        freqs.computeIfAbsent(signalRadio.freq) { mutableListOf() }.add(signalRadio)
    }
}
