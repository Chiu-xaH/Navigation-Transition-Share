package com.xah.sample.ui.util

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import com.xah.transition.state.TransitionConfig

object MyAnimationManager  {
    data class TransferAnimation(val remark : String,val enter : EnterTransition, val exit : ExitTransition)

    @OptIn(ExperimentalSharedTransitionApi::class)
    fun getCenterBoundsTransform() = BoundsTransform { _, _ ->//FastOutSlowInEasing
        tween(durationMillis = TransitionConfig.curveStyle.speedMs, easing = FastOutSlowInEasing)
    }

    fun getCenterAnimation() = TransferAnimation(
        "向中心运动",
        scaleIn(animationSpec =  tween(durationMillis = TransitionConfig.curveStyle.speedMs, easing = LinearOutSlowInEasing), initialScale = .8f) + fadeIn(animationSpec = tween(durationMillis = TransitionConfig.curveStyle.speedMs/2)),
        scaleOut(animationSpec =  tween(durationMillis = TransitionConfig.curveStyle.speedMs,easing = LinearOutSlowInEasing), targetScale = .8f) + fadeOut(animationSpec = tween(durationMillis = TransitionConfig.curveStyle.speedMs/2))
    )
}
