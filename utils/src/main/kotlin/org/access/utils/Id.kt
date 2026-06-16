package org.access.utils

object Id {
    var idIndex: Int = 0
    fun genId(): Int {
        idIndex++
        return idIndex
    }
}