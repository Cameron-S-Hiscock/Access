package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.BridgeRepository

object Controller {
    val id: UUID = Id.genId(this)
    init { Id.objectIds.putIfAbsent("Controller", id) }

    val CoreProcessWorker = ProcessWorker(
        name = "CoreProcessWorker",
        host = id,
    )
    init {
        CoreProcessWorker.run()
        CoreProcessWorker.start()

        CoreProcessWorker.submitWork(
            CoreProcessWorker.taskFactory.create(name = "UICoreBridgeCoreReceiver") { 
                val IO = BridgeRepository.iostreams["UICoreBridge"]
                require(IO != null)
                val message = IO.receive(author = id)
                message?.task?.action()
            }
        )

        CoreProcessWorker.submitWork(
            CoreProcessWorker.taskFactory.create(name = "UICoreBridgeCoreTest") {
                val IO = BridgeRepository.iostreams["UICoreBridge"]
                require(IO != null)
                IO.send(
                    author = id,
                    message = CoreProcessWorker.messageFactory.create(
                        name = "UICoreBridgeUITestMessage",
                        origin = id,
                        destination = IO.id,
                        task = CoreProcessWorker.taskFactory.create(name = "UICoreBridgeCoreTestPrint") { println("UICoreBridgeUITestArrived") },
                    )
                )
            }
        )
    }
}
