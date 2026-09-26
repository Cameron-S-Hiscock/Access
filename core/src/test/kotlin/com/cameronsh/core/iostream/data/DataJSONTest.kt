package com.cameronsh.core.iostream.data

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.*

class DataJSONTest : FunSpec({
    test("data object created is encoded and then decoded into JSON") {
        val dataSerialized = CompletableDeferred<Boolean>()
        val dataDeserialized = CompletableDeferred<Boolean>()
    }
})