package com.cameronsh.data

import com.cameronsh.utils.Id
import java.util.UUID

object SQLDB() {
    val id: UUID = Id.genId(this)

    val db by lazy {
        Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")
    }

    suspend fun initDB() {

    }
}