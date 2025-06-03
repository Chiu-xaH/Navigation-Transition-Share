package com.xah.sample.ui.screen

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.screen.detail.DetailScreen
import com.xah.sample.ui.screen.home.HomeScreen
import com.xah.sample.ui.screen.settings.SettingsScreen
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.ui.util.isCurrentRoute
import com.xah.sample.ui.util.navigateAndSaveForTransition
import com.xah.sample.viewmodel.UIViewModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val vm = remember { UIViewModel() }
    MaterialTheme {
        AppNavHost(vmUI = vm)
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(vmUI : UIViewModel) {
    val navController = rememberNavController()
    val firstRoute = remember { ScreenRoute.HomeScreen.route }
    vmUI.isExpandedScreen = !navController.isCurrentRoute(firstRoute)
    val isExpanded by remember { derivedStateOf { vmUI.isExpandedScreen } }
    val isCenterAnimation by remember { derivedStateOf { vmUI.isCenterAnimation } }


    // 延时固定时间后，显示UI
    var showSurface by remember { mutableStateOf(false) }
    LaunchedEffect(isExpanded) {
        if(isExpanded) {
            // 延时懒加载二级界面 减少因界面渲染和动画过程同步而掉帧
            showSurface = false
            delay(MyAnimationManager.ANIMATION_SPEED*1L)
            showSurface = true
        } else {
            showSurface = false
        }
    }

    val boundsTransform = if(!isCenterAnimation) {
        MyAnimationManager.defaultBoundsTransform
    } else {
        MyAnimationManager.centerBoundsTransform
    }
    SharedTransitionLayout(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
        NavHost(
            navController = navController,
            startDestination = firstRoute
        ) {
            composable(firstRoute) {
                HomeScreen(
                    vmUI,
                    navController,
                    this@SharedTransitionLayout,
                    this@composable,
                    boundsTransform
                ) {
                    navController.navigateAndSaveForTransition(it)
                }
            }
            composable(ScreenRoute.SettingsScreen.route) {
                SettingsScreen(
                    vmUI,
                    showSurface,
                    this@SharedTransitionLayout,
                    this@composable,
                    boundsTransform,
                ) {
                    navController.navigateAndSaveForTransition(firstRoute)
                }
            }
            for(i in 1 until 31) {
                (ScreenRoute.ModuleScreen.route + i).let { route ->
                    composable(route) {
                        DetailScreen(
                            vmUI,
                            showSurface,
                            route,
                            this@SharedTransitionLayout,
                            this@composable,
                            boundsTransform,
                        ) {
                            navController.navigateAndSaveForTransition(firstRoute)
                        }
                    }
                }
            }
        }
    }
}
