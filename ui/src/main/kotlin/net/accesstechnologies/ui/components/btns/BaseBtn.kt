package net.accesstechnologies.ui.components.btns

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseBtn(
    // action and content must be lambdas
    // modifier is optional
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