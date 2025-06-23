package com.xah.sample.ui.style

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.navigation.NavHostController
import com.xah.sample.ui.component.largeCardColor
import com.xah.sample.ui.util.MyAnimationManager.ANIMATION_SPEED
import com.xah.sample.ui.util.currentRoute
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

object TransitionState {
    var transplantBackground = false
}

@Composable
fun transitionBackground(navHostController: NavHostController, route : String, vm : UIViewModel) : Modifier {
    val motionBlur = vm.motionBlur
    val transition = vm.forceAnimation
    val transplantBackground = TransitionState.transplantBackground
    val isExpanded = navHostController.currentRoute() != route
    // 稍微晚于运动结束
    val blurSize by animateDpAsState(
        targetValue = if (isExpanded && motionBlur) APP_BLUR_RADIUS else 0.dp, label = ""
        ,animationSpec = tween(ANIMATION_SPEED + ANIMATION_SPEED/2, easing = FastOutSlowInEasing),
    )
    val scale = animateFloatAsState( //.875f
        targetValue = if (isExpanded) 0.875f else 1f,
        animationSpec = tween(ANIMATION_SPEED+ ANIMATION_SPEED/2 , easing = FastOutSlowInEasing)
    )
    val backgroundColor by animateColorAsState(
        targetValue = if(isExpanded) Color.Black.copy(.5f) else Color.Transparent,
        animationSpec = tween(ANIMATION_SPEED, easing = FastOutSlowInEasing)
    )
    // 蒙版 遮罩
    if(transition && !transplantBackground)
        Box(modifier = Modifier.fillMaxSize().background(backgroundColor).zIndex(2f))

    val transitionModifier = if(transition) Modifier.scale(scale.value).blur(blurSize) else Modifier

    // 延时固定时间后，显示UI
//    LaunchedEffect(isExpanded) {
//        if(isExpanded) {
////             延时懒加载二级界面 减少因界面渲染和动画过程同步而掉帧
//            vm.showSurface = false
//            delay(ANIMATION_SPEED *1L)
//            vm.showSurface = true
//        }
//    }

    return transitionModifier
}
