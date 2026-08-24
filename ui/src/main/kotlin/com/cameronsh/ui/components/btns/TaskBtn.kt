package com.cameronsh.ui.components.btns

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.cameronsh.core.iostream.task.TaskFactory

import com.cameronsh.ui.components.btns.BaseBtn

@Composable
fun TaskBtn(
    taskFactory: TaskFactory,
) {
    BaseBtn(
        name = "TaskTestBtn",
        taskFactory = taskFactory,
        action = { println("Running a Task") }
    ) { Text("Create Task") }
}
