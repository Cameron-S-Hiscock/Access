package com.cameronsh.ui.components.btns

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import com.cameronsh.core.models.tasks.TaskFactory
import com.cameronsh.ui.components.btns.BaseBtn

@Composable
fun TaskBtn(
    taskFactory: TaskFactory
) {
    BaseBtn(
        action = taskFactory.create(
            name = "task",
            action = { println("Running a task") }
        ).action,
    ) { Text("Create Task") }
}
