package com.cameronsh.data.db

import com.cameronsh.utils.Id
import java.util.UUID

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Database

object UserDB : SQLDB() {
    override val db by lazy {
        Database.connect("jdbc:sqlite:/data/user.db", "org.sqlite.JDBC")
    }
}