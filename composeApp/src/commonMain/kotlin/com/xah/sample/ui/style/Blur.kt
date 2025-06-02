package com.xah.sample.ui.style

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.xah.sample.ui.component.largeCardColor
import com.xah.sample.ui.util.MyAnimationManager.ANIMATION_SPEED
import com.xah.sample.viewmodel.UIViewModel

val APP_BLUR_RADIUS = 10.dp
@Composable
fun appBlur(
    vm : UIViewModel,
    showBlur: Boolean,
    radius: Dp = APP_BLUR_RADIUS,
    tweenDuration: Int = ANIMATION_SPEED / 2
): Modifier {
    val motionBlur = vm.motionBlur
//    by DataStoreManager.motionBlurFlow.collectAsState(initial = AppVersion.CAN_MOTION_BLUR)

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


// isExpanded=true时，下层背景进入高斯模糊，并用黑色压暗，伴随缩放，上层背景展开
@Composable
fun transitionBackground(isExpanded : Boolean,vm : UIViewModel) : Modifier {
    val motionBlur = vm.motionBlur
    val transition = vm.forceAnimation
    // 稍微晚于运动结束
    val blurSize by animateDpAsState(
        targetValue = if (isExpanded && motionBlur) 12.dp else 0.dp, label = ""
        ,animationSpec = tween(ANIMATION_SPEED + 100, easing = LinearOutSlowInEasing),
    )
    val scale = animateFloatAsState(
        targetValue = if (isExpanded) 0.8f else 1f, // 按下时为0.9，松开时为1
        animationSpec = tween(ANIMATION_SPEED + 100, easing = LinearOutSlowInEasing)
    )
    val backgroundColor by animateColorAsState(
        targetValue = if(isExpanded) Color.Black.copy(.3f) else Color.Transparent,
        animationSpec = tween(ANIMATION_SPEED, easing = LinearOutSlowInEasing)
    )
    // 蒙版
    if(transition)
        Box(modifier = Modifier.fillMaxSize().background(backgroundColor).zIndex(2f))

    val transitionModifier = if(transition) Modifier.blur(blurSize).scale(scale.value) else Modifier
    return transitionModifier
}