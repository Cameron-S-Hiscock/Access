package net.accesstechnologies.ui

import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.window.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.*

import net.accesstechnologies.ui.components.btns.BaseBtn
import net.accesstechnologies.ui.components.btns.ExitBtn
import net.accesstechnologies.ui.components.btns.RestartBtn

@Composable
fun App() {
    Box( modifier = Modifier.background(Color.Black).fillMaxSize()) {
        BaseBtn({ println("Hello World!")}) { Text("Hello World!") }
        Box(modifier = Modifier.align(Alignment.TopEnd)) { ExitBtn() }
        Box(modifier = Modifier.align(Alignment.BottomEnd)) { RestartBtn() }
    }
}

@Composable
fun ApplicationScope.Composer() {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Access",
        state = rememberWindowState(placement = WindowPlacement.Maximized),
        alwaysOnTop = false
    ) {
        App()
    }
}