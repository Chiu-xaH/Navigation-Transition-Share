package com.xah.sample.ui.util

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object MyAnimationManager {
    data class TransferAnimation(val remark : String,val enter : EnterTransition, val exit : ExitTransition)

    const val ANIMATION_SPEED = 400

    private val enterAnimation2 = scaleIn(animationSpec =  tween(durationMillis = ANIMATION_SPEED, easing = LinearOutSlowInEasing), initialScale = .8f) + fadeIn(animationSpec = tween(durationMillis = ANIMATION_SPEED/2))

    private val exitAnimation2 = scaleOut(animationSpec =  tween(durationMillis = ANIMATION_SPEED,easing = LinearOutSlowInEasing), targetScale = .8f) + fadeOut(animationSpec = tween(durationMillis = ANIMATION_SPEED/2))

    val centerAnimation = TransferAnimation("向中心运动",enterAnimation2, exitAnimation2)

    private val enterAnimation5 = scaleIn(animationSpec =  tween(durationMillis = ANIMATION_SPEED, easing = LinearOutSlowInEasing))

    private val exitAnimation5 = scaleOut(animationSpec =  tween(durationMillis = ANIMATION_SPEED,easing = LinearOutSlowInEasing))

    val centerFadeAnimation = TransferAnimation("向中心完全运动",enterAnimation5, exitAnimation5)

    private val enterAnimationFade = fadeIn(animationSpec = tween(durationMillis = ANIMATION_SPEED))

    private val exitAnimationFade = fadeOut(animationSpec = tween(durationMillis = ANIMATION_SPEED))

    val fadeAnimation = TransferAnimation("淡入淡出", enterAnimationFade, exitAnimationFade)

    private val enterAnimationNull = fadeIn(animationSpec = tween(durationMillis = 0))

    private val exitAnimationNull = fadeOut(animationSpec = tween(durationMillis = 0))

    val nullAnimation = TransferAnimation("无", enterAnimationNull, exitAnimationNull)

    private val enterRightAnimation = slideInHorizontally(
        initialOffsetX = { it }, // 从右侧进入
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
    )
    private val exitRightAnimation = slideOutHorizontally(
        targetOffsetX = { it }, // 向右侧隐藏
        animationSpec = tween(durationMillis = ANIMATION_SPEED)
    )
    val hiddenRightAnimation = TransferAnimation("向右侧边隐藏", enterRightAnimation, exitRightAnimation)

    private val enterLeftAnimation = slideInHorizontally(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow))
    private val exitLeftAnimation = slideOutHorizontally(animationSpec = tween(durationMillis = ANIMATION_SPEED))
    val hiddenLeftAnimation = TransferAnimation("向左侧边隐藏", enterRightAnimation, exitRightAnimation)
}
