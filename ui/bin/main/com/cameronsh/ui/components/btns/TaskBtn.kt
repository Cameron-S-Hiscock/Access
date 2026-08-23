package com.cameronsh.ui.components.btns

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import com.cameronsh.ui.components.btns.BaseBtn

@Composable
fun TaskBtn(
) {
    BaseBtn(
        name = "TaskTestBtn",
        action = { println("Running a Task") }
    ) { Text("Create Task") }
}
