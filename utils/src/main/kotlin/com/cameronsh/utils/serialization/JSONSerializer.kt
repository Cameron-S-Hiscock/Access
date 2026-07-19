package com.cameronsh.utils.serialization

import kotlinx.serialization.*
import kotlinx.serialization.json.*

object JSONSerializer {
    inline fun <reified T> encode(obj: T): String {
        val json = Json.encodeToString(obj)
        return json
    }
    
    inline fun <reified T> decode(json: String): T {
        val obj = Json.decodeFromString<T>(json)
        return obj
    }
}
