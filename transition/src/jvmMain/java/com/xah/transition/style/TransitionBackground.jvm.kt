package com.xah.transition.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
@Composable
actual fun Modifier.blurEffect(radius: Dp): Modifier = this.blur(radius)