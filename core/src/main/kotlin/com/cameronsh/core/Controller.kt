package com.cameronsh.core

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.ProcessWorker
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.iostream.data.DataFactory

object Controller {
    val id: UUID = Id.genId(this)

    fun initMainProcess() {
        val dataFactory = DataFactory()
        val taskFactory = TaskFactory(dataFactory = dataFactory)
        val mainProcess = ProcessWorker(name = "ControllerProcessWorker", host = id)

        val greetTask = taskFactory.create(
            name = "GreetTask",
            action = { println("Hello World!") },
        )
        mainProcess.start()
        mainProcess.addWork(
            taskFactory.create(name = "${mainProcess.name}AddWork") { mainProcess.submitTask(greetTask) }
        )
        mainProcess.addWork(
            taskFactory.create(name = "${mainProcess.name}StartWorkers") { mainProcess.run() }
        )
    }
}
