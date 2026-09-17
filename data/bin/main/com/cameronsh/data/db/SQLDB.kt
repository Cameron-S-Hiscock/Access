package com.cameronsh.data.db

import com.cameronsh.utils.Id
import java.util.UUID

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Database

open class SQLDB() {
    val id: UUID = Id.genId(this)

    open val db by lazy {
        Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")
    }

    suspend open fun initDB() {

    }

    suspend open fun rateData() {

    }

    suspend open fun sendData() {
        
    }
}