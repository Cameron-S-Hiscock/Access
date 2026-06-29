package net.accesstechnologies.ui

import net.accesstechnologies.utils.Id

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
import net.accesstechnologies.ui.components.btns.TaskBtn

import net.accesstechnologies.core.models.tasks.TaskFactory
import net.accesstechnologies.core.registry.RegistryService

object Composer {
    val id: String = Id.genId()

    @Composable
    fun App() {
        Box( modifier = Modifier.background(Color.Black).fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.TopEnd)) { ExitBtn() }
            Box(modifier = Modifier.align(Alignment.BottomEnd)) { RestartBtn() }
            Box(modifier = Modifier.align(Alignment.Center)) { TaskBtn(TaskFactory) }
        }
    }

    @Composable
    fun ApplicationScope.Compose() {
        println("COMPOSER: COMPOSE: EXECUTION")
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