package com.cameronsh.ui

import com.cameronsh.utils.Id

import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.window.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.*

import com.cameronsh.ui.components.btns.BaseBtn
import com.cameronsh.ui.components.btns.ExitBtn
import com.cameronsh.ui.components.btns.RestartBtn
import com.cameronsh.ui.components.btns.TaskBtn

object Composer {
    val id: String = Id.genId()

    @Composable
    fun App() {
        Box( modifier = Modifier.background(Color.Black).fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.TopEnd)) { ExitBtn() }
            Box(modifier = Modifier.align(Alignment.BottomEnd)) { RestartBtn() }
            Box(modifier = Modifier.align(Alignment.Center)) { TaskBtn() }
        }
    }

    @Composable
    fun ApplicationScope.Compose() {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Access",
            state = rememberWindowState(placement = WindowPlacement.Maximized),
            alwaysOnTop = false
        ) {
            App()
        }
    }
}
