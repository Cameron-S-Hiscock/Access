package com.cameronsh.ui.components.btns

import kotlin.system.exitProcess

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import com.cameronsh.ui.components.btns.BaseBtn
import com.cameronsh.core.models.task.TaskPriority

@Composable
fun ExitBtn() {
    BaseBtn(
        name = "ExitBtn",
        priority = TaskPriority.CRITICAL,
        action = { exitProcess(0) }
    ) {
        Text("Exit")
    }
}
