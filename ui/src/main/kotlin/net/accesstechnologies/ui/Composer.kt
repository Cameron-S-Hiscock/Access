package net.accesstechnologies.ui

import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.window.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.*

import net.accesstechnologies.ui.assets.btns.ExitBtn

@Composable
fun App() {
    Box( modifier = Modifier.background(Color.Black).fillMaxSize()) {
        Button(onClick = { println("Hello World!") }) { Text("Hello World!") }
        ExitBtn()
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