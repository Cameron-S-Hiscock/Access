package org.access.ui.assets.btns

import kotlin.system.exitProcess

import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun ExitBtn() {
    Box(
        modifier = Modifier.padding(8.dp).fillMaxSize()
    ) {
        Button(
            onClick = { exitProcess(0) },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Text("Exit")
        }
    }
}