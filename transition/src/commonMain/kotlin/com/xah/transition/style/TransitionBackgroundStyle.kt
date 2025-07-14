package com.xah.transition.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TransitionBackgroundStyle (
    var motionBlur : Boolean = true,
    var forceTransition : Boolean = true,
    var blurRadius : Dp = 10.dp,
    var backgroundColor : Color = Color.Black.copy(.5f),
    var scaleValue : Float = 0.875f
)