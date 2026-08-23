package com.cameronsh.ui.components.btns

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import com.cameronsh.ui.components.btns.BaseBtn

@Composable
fun RestartBtn() {
    BaseBtn(
        name = "RestartBtn",
        action = { println("Restarting Access") }
    ) {
        Text("Restart")
    }
}
