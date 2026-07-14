package com.cameronsh.utils

object StrToType {
    fun convert(strtype: String): T {
        val type when(strtype) {
            "Byte" -> Byte
            "Short" -> Short
            "Int" -> Int
            "Long" -> Long
            "Float" -> Float
            "Double" -> Double
            "Char" -> Char
            "String" -> String
            "Boolean" -> Boolean
            else -> strtype
        }
        return type
    }
}
