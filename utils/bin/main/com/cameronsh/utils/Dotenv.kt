package com.cameronsh.utils

import java.util.UUID

import io.github.cdimascio.dotenv.dotenv

object Dotenv {
    val id: UUID = Id.genId(this)

    val dotenv = dotenv()
}