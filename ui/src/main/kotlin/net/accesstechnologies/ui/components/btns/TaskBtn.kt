package net.accesstechnologies.ui.components.btns

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import net.accesstechnologies.core.models.tasks.TaskFactory
import net.accesstechnologies.ui.components.btns.BaseBtn

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