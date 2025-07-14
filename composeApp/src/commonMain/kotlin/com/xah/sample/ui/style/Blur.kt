package com.xah.sample.ui.style

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xah.sample.ui.component.largeCardColor
import com.xah.transition.state.TransitionState

val APP_BLUR_RADIUS = 10.dp
@Composable
fun appBlur(
    showBlur: Boolean,
    radius: Dp = APP_BLUR_RADIUS,
    tweenDuration: Int = TransitionState.curveStyle.speedMs / 2
): Modifier {
    val motionBlur = TransitionState.transitionBackgroundStyle.motionBlur

    val blurRadius by animateDpAsState(
        targetValue = if (showBlur && motionBlur) radius else 0.dp,
        animationSpec = tween(tweenDuration, easing = LinearOutSlowInEasing),
    )

    val overlayAlpha by animateFloatAsState(
        targetValue = if (showBlur && !motionBlur) 1f else 0f,
        animationSpec = tween(tweenDuration, easing = LinearOutSlowInEasing),
    )
    val color = largeCardColor()

    return Modifier
        .then(if (motionBlur) Modifier.blur(blurRadius) else Modifier)
        .drawWithContent {
            drawContent()
            if (!motionBlur && overlayAlpha > 0f) {
                drawRect(
                    color = color.copy(alpha = overlayAlpha)
                )
            }
        }
}