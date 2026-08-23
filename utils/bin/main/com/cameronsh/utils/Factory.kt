package com.cameronsh.utils

interface Factory {
    val id: UUID

    fun create()
}
