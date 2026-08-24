package com.cameronsh.ui

import com.cameronsh.utils.Id
import java.util.UUID

import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.window.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.*
import com.cameronsh.core.iostream.data.DataFactory
import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.registry.RegistryWorker
import java.util.concurrent.LinkedBlockingDeque
import com.cameronsh.core.iostream.task.Task

import com.cameronsh.ui.components.btns.BaseBtn
import com.cameronsh.ui.components.btns.ExitBtn
import com.cameronsh.ui.components.btns.RestartBtn
import com.cameronsh.ui.components.btns.TaskBtn

object Composer {
    val id: UUID = Id.genId(this)

    val RSETasks = LinkedBlockingDeque<Task>()
    val dataFactory = DataFactory()
    val taskFactory = TaskFactory(
        dataFactory =  dataFactory,
    )
    val registryWorker = RegistryWorker(
        RSETasks = RSETasks,
        taskFactory = taskFactory,
    )

    @Composable
    fun App() {
        Box( modifier = Modifier.background(Color.Black).fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.TopEnd)) { ExitBtn(taskFactory = taskFactory) }
            Box(modifier = Modifier.align(Alignment.BottomEnd)) { RestartBtn(taskFactory = taskFactory) }
            Box(modifier = Modifier.align(Alignment.Center)) { TaskBtn(taskFactory = taskFactory) }
            Box(modifier = Modifier.align(Alignment.BottomStart)) { BaseBtn(name = "default", taskFactory = taskFactory, action = { println("Btn pressed") }) { Text("Default") } }
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
