package com.cameronsh.ui.components.btns

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.ui.components.btns.BaseBtn

@Composable
fun RestartBtn(
    taskFactory: TaskFactory,
) {
    BaseBtn(
        name = "RestartBtn",
        taskFactory = taskFactory,
        action = { println("Restarting Access") }
    ) {
        Text("Restart")
    }
}
