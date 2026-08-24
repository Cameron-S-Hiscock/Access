package com.cameronsh.ui.components.btns

import com.cameronsh.utils.Id
import java.util.UUID

import com.cameronsh.core.iostream.task.TaskFactory
import com.cameronsh.core.iostream.task.TaskPriority

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding

@Composable
fun BaseBtn(
    // NOTE: action and content must be lambdas
    // NOTE: modifier is optional
    name: String = "Btn",
    priority: TaskPriority = TaskPriority.NORMAL,
    taskFactory: TaskFactory,
    action: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
    
) {
    Button(
        onClick = { taskFactory.create("${name}", priority = priority, action = { action }).action },
        modifier = modifier.padding(8.dp)
    ) {
        content()
    }
}
