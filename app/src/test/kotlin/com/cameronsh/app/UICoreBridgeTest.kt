package com.cameronsh.app

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import com.cameronsh.api.UICoreBridge
import com.cameronsh.core.Controller
import com.cameronsh.ui.Composer
import com.cameronsh.core.bridge.BridgeRepository
import com.cameronsh.core.bridge.Bridge
import com.cameronsh.core.ProcessWorker
import kotlinx.coroutines.*
import com.cameronsh.core.iostream.IOStream

class UICoreBridgeTest : FunSpec({
    test("message sent by Controller from Core through UICoreBridge arrvies at Composer in UI") {
        runBlocking {
            UICoreBridge.init()
            Controller.init()
            Composer.init()
            // bridge = BridgeRepository.bridges["UICoreBridge"] as Bridge
            // bridge.init()
        }

        val coreArrived = CompletableDeferred<Boolean>()

        val CoreTestProcessWorker = ProcessWorker(
            name = "CoreTestProcessWorker",
            host = Controller.id,
        )
        val UITestProcessWorker = ProcessWorker(
            name = "UITestProcessWorker",
            host = Composer.id,
        )

        UICoreBridge.send(
            authorId = Controller.id,
            message = CoreTestProcessWorker.messageFactory.create(
                name = "ControllerUICoreBridgeCoreTestMessage",
                origin = Controller.id,
                destination = UICoreBridge.id,
                task = CoreTestProcessWorker.taskFactory.create(name = "ControllerUICoreBridgeCoreTestMessageAction") {
                    coreArrived.complete(true)
                }
            )
        )

        val message = UICoreBridge.receive(Composer.id)
        message?.task?.action()

        withTimeout(2000) {
            coreArrived.await() shouldBe true
        }
    }
    
    test("message sent by Composer through UICoreBridge arrvies at Controller") {
        runBlocking {
            // UICoreBridge.init()
            // Controller.init()
            // Composer.init()
            // bridge = BridgeRepository.bridges["UICoreBridge"] as Bridge
            // bridge.init()
        }

        val uiArrived = CompletableDeferred<Boolean>()

        val UITestProcessWorker = ProcessWorker(
            name = "UITestProcessWorker",
            host = Composer.id,
        )
        val CoreTestProcessWorker = ProcessWorker(
            name = "CoreTestProcessWorker",
            host = Controller.id,
        )

        UICoreBridge.send(
            authorId = Composer.id,
            message = UITestProcessWorker.messageFactory.create(
                name = "ComposerUICoreBridgeCoreTestMessage",
                origin = Composer.id,
                destination = UICoreBridge.id,
                task = UITestProcessWorker.taskFactory.create(name = "ComposerUICoreBridgeCoreTestMessageAction") {
                    uiArrived.complete(true)
                }
            )
        )

        val message = UICoreBridge.receive(Controller.id)
        message?.task?.action()

        withTimeout(2000) {
            uiArrived.await() shouldBe true
        }
    }
})