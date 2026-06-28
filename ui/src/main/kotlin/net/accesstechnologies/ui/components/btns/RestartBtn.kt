package net.accesstechnologies.ui.components.btns

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import net.accesstechnologies.ui.components.btns.BaseBtn

@Composable
fun RestartBtn() {
    BaseBtn(
        { println("Restarting Access") }
    ) {
        Text("Restart")
    }
}