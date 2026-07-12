package com.cameronsh.ui.components.btns

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding

@Composable
fun BaseBtn(
    // NOTE: action and content must be lambdas
    // NOTE: modifier is optional
    action: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
    
) {
    Button(
        onClick = action,
        modifier = modifier.padding(8.dp)
    ) {
        content()
    }
}
