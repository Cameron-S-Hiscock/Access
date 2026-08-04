package com.cameronsh.data.network

import com.cameronsh.utils.Id
import java.util.UUID

object Router {
    val id: UUID = Id.genId(this)

    suspend fun networkConnect(networkPort: Int) {

    }

    suspend fun networkDisconnect(networkPort: Int) {

    }

    suspend fun networkCall() {

    }
}
