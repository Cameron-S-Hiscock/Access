package com.cameronsh.core.iostream.task

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.*
import com.cameronsh.core.iostream.data.DataFactory

class TaskCreationTest : FunSpec({
    test("create Task object from scratch") {
        var run = false

        val task = Task(
            name = "TaskCreationScratchTest",
            dataFactory = DataFactory(),
        ) {
            run = true
        }

        runBlocking {
            task.action()
        }

        task.name shouldBe "TaskCreationScratchTest"
        task.state shouldBe TaskState.PENDING
        task.priority shouldBe TaskPriority.NORMAL
        run shouldBe true
    }

    test("create Task object with a TaskFactory") {
        var run = false

        val taskFactory = TaskFactory()
        val task = taskFactory.create(
            name = "TaskCreationFactoryTest",
        ) {
            run = true
        }

        runBlocking {
            task.action()
        }

        task.name shouldBe "TaskCreationFactoryTest"
        task.state shouldBe TaskState.PENDING
        task.priority shouldBe TaskPriority.NORMAL
        run shouldBe true
    }
})