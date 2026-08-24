package com.cameronsh.ui.components.btns

import kotlin.system.exitProcess

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.ui.components.btns.BaseBtn
import com.cameronsh.core.iostream.task.TaskPriority

@Composable
fun ExitBtn(
    taskFactory: TaskFactory,
) {
    BaseBtn(
        name = "ExitBtn",
        priority = TaskPriority.CRITICAL,
        taskFactory = taskFactory,
        action = { exitProcess(0) }
    ) {
        Text("Exit")
    }
}
