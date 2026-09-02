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
        CoreProcessWorker.start()
        CoreProcessWorker.addWork(
            CoreProcessWorker.taskFactory.create(name = "${CoreProcessWorker.name}StartWorkers") { CoreProcessWorker.run() }
        )
        CoreProcessWorker.addWork(
            CoreProcessWorker.taskFactory.create(name = "UICoreBridgeCoreTest") {
                val IO = BridgeRepository.iostreams["UICoreBridge"]
                require(IO != null)
                IO.send(
                    author = id,
                    message = CoreProcessWorker.messageFactory.create(
                        name = "UICoreBridgeUITestMessage",
                        origin = id,
                        destination = IO.id,
                        task = CoreProcessWorker.taskFactory.create(name = "UICoreBridgeCoreTestMessageTask") { println("UICoreBridgeUITestMessageArrived") },
                    )
                )
            }
        )
    }

    fun initMainProcess() {
        val taskFactory = TaskFactory()
        val mainProcess = ProcessWorker(name = "ControllerProcessWorker", host = id)

        val greetTask = taskFactory.create(
            name = "GreetTask",
            action = { println("Hello World!") },
        )
        mainProcess.start()
        mainProcess.addWork(
            taskFactory.create(
                name = "${mainProcess.name}AddWork",
                action = { mainProcess.submitTask(greetTask) },
            )
        )
        mainProcess.addWork(
            taskFactory.create(name = "${mainProcess.name}StartWorkers") { mainProcess.run() }
        )
    }
}
