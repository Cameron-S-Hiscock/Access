package com.cameronsh.app

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import com.cameronsh.api.UICoreBridge
import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.core.BridgeRepository
import com.cameronsh.core.ProcessWorker
import kotlinx.coroutines.*

class UICoreBridgeTest : FunSpec({
    test("message sent by Controller from Core through UICoreBridge arrvies at Composer in UI") {
        withContext(Dispatchers.Default) { this.launch {
            UICoreBridge.init()
            Controller.init()
            Composer.init()
        } }

        val IO = BridgeRepository.iostreams["UICoreBridge"]
        require(IO != null)

        val coreArrived = CompletableDeferred<Boolean>()

        val CoreTestProcessWorker = ProcessWorker(
            name = "CoreTestProcessWorker",
            host = Controller.id,
        )

        CoreTestProcessWorker.submitWork(
            CoreTestProcessWorker.taskFactory.create(name = "ControllerUICoreBridgeCoreTest") {
                IO.send(
                    author = Controller.id,
                    message = CoreTestProcessWorker.messageFactory.create(
                        name = "ControllerUICoreBridgeCoreTestMessage",
                        origin = Controller.id,
                        destination = IO.id,
                        task = CoreTestProcessWorker.taskFactory.create(name = "ControllerUICoreBridgeCoreTestMessageAction") {
                            coreArrived.complete(true)
                        }
                    )
                )
            }
        )

        withTimeout(2000) {
            coreArrived.await() shouldBe true
        }
    }
    
    test("message sent by Composer through UICoreBridge arrvies at Controller") {
        // withContext(Dispatchers.Default) { this.launch {
            // UICoreBridge.init()
            // Controller.init()
            // Composer.init()
        // } }

        val IO = BridgeRepository.iostreams["UICoreBridge"]
        require(IO != null)

        val uiArrived = CompletableDeferred<Boolean>()

        val UITestProcessWorker = ProcessWorker(
            name = "UITestProcessWorker",
            host = Composer.id,
        )

        UITestProcessWorker.submitWork(
            UITestProcessWorker.taskFactory.create(name = "ComposerUICoreBridgeCoreTest") {
                IO.send(
                    author = Composer.id,
                    message = UITestProcessWorker.messageFactory.create(
                        name = "ComposerUICoreBridgeCoreTestMessage",
                        origin = Composer.id,
                        destination = IO.id,
                        task = UITestProcessWorker.taskFactory.create(name = "ComposerUICoreBridgeCoreTestMessageAction") {
                            uiArrived.complete(true)
                        }
                    )
                )
            }
        )

        withTimeout(2000) {
            uiArrived.await() shouldBe true
        }
    }
})