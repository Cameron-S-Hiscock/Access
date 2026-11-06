package com.csh_s.data.db

import com.csh_s.utils.Id
import java.util.UUID

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Database

object CAGISDB : SQLDB() {
    override val db by lazy {
        Database.connect("jdbc:sqlite:/data/cagis.db", "org.sqlite.JDBC")
    }
}