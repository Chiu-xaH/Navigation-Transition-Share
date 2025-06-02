package com.xah.sample.ui.style

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RowHorizontal(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Row(horizontalArrangement = Arrangement.Center, modifier = modifier) {
        content() // 插入传入的布局内容
    }
}


@Composable
fun ColumnVertical(modifier: Modifier = Modifier,content: @Composable () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        content()
    }
}