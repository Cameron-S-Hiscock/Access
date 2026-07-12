package com.cameronsh.ui.components.btns

import kotlin.system.exitProcess

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import com.cameronsh.ui.components.btns.BaseBtn

@Composable
fun ExitBtn() {
    BaseBtn(
        { exitProcess(0) }
    ) {
        Text("Exit")
    }
}
