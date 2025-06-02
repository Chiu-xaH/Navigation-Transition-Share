package com.xah.sample.ui.style

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState

private val BLUR_RADIUS = 20.dp
@Composable
fun Modifier.topBarStyle() : Modifier {
    val surfaceColor = MaterialTheme.colorScheme.surface
    return this.background(Brush.verticalGradient(
        listOf(surfaceColor, Color.Transparent)
    ))
}



