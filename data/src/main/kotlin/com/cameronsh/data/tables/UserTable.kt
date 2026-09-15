package com.cameronsh.data.tables

import com.cameronsh.utils.Id
import java.util.UUID

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable

const val MAX_VARCHAR_LENGTH = 255

object UserTable : Table("users") {
    val id = uuid("id").clientDefault { Id.genId(this) }
    val name = varchar("name", MAX_VARCHAR_LENGTH)
    override val primaryKey = PrimaryKey(id)
}