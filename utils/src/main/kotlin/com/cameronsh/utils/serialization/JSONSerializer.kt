package com.cameronsh.utils.serialization

import com.cameron.utils.StrToType

import kotlinx.serialization.*
import kotlinx.serialization.json.*

object JSONSerialzer {
    val jsons = mutableListOf<String>()

    fun encode(obj) {
        val jsonobj = obj.Json.encodeToString(obj)
        jsons.add(jsonobj)
    }

    fun decode(jsonobj: String, strtype: String) {
        val type = StrToType.convert(strtype)
        val obj = jsonobj.decodeFromString<type>(jsonobj)
    }
}
