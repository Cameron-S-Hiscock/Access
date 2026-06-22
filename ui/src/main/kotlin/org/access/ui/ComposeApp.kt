package org.access.ui

import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.window.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

fun ApplicationScope.createWindow() {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}