package com.xah.sample.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.xah.sample.ui.style.TransitionState
import com.xah.sample.ui.util.MyAnimationManager.ANIMATION_SPEED
import com.xah.sample.ui.util.isCurrentRoute
import com.xah.sample.ui.util.isInBottom
import com.xah.sample.ui.util.previousRoute
import kotlinx.coroutines.delay

@Composable
fun CustomScaffold(
    route: String,
    navHostController : NavController,
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable (() -> Unit) = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor : Color? = null,
    content: @Composable ((PaddingValues) -> Unit)
) {
    // 当从CustomScaffold1向CustomScaffold2时，CustomScaffold2先showSurface=false再true，而CustomScaffold1一直为true
    val isCurrentEntry = navHostController.isCurrentRoute(route)
    val isPreviousEntry = navHostController.previousRoute() == route
    // 当回退时，即从CustomScaffold2回CustomScaffold1时，CustomScaffold2立刻showSurface=false，而CustomScaffold1一直为true
    var show by rememberSaveable(route) { mutableStateOf(false) }

    LaunchedEffect(isCurrentEntry) {
        // 当前页面首次进入时播放动画
        if (isCurrentEntry && !show) {
            show = false
            delay(ANIMATION_SPEED* 1L)
            show = true
        } else if(show) {
            if(navHostController.isInBottom(route)) {
                return@LaunchedEffect
            }
            show = false
        }
    }

    // 回退后恢复上一个页面的显示状态
    LaunchedEffect(isPreviousEntry) {
        if (isPreviousEntry) {
            show = true
        }
    }
//    LaunchedEffect(isCurrentEntry,isPreviousEntry) {
//        println("route $route | current $isCurrentEntry| previous $isPreviousEntry")
//    }

    Scaffold(
        containerColor = containerColor ?: if(TransitionState.transplantBackground) Color.Transparent else MaterialTheme.colorScheme.surface,
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
    ) { innerPadding ->
        AnimatedVisibility(
            visible = show,
            enter  = if(ANIMATION_SPEED == 0) fadeIn(tween(durationMillis = 0)) else fadeIn(),
            exit = fadeOut(tween(durationMillis = 0))
        ) {
            content(innerPadding)
        }
    }
}