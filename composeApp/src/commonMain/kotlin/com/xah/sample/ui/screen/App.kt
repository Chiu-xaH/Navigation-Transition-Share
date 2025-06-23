package com.xah.sample.ui.screen

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.screen.detail.DetailScreen
import com.xah.sample.ui.screen.detail.DetailScreenR
import com.xah.sample.ui.screen.home.HomeScreen
import com.xah.sample.ui.screen.settings.SettingsScreen
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.ui.util.MyAnimationManager.ANIMATION_SPEED
import com.xah.sample.ui.util.allRouteStack
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
    val isCenterAnimation by remember { derivedStateOf { vmUI.isCenterAnimation } }

    val boundsTransform = if(!isCenterAnimation) {
        MyAnimationManager.defaultBoundsTransform
    } else {
        MyAnimationManager.centerBoundsTransform
    }
    SharedTransitionLayout(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        NavHost(
            navController = navController,
            startDestination = firstRoute,
            enterTransition = {
                MyAnimationManager.fadeAnimation.enter
            },
            exitTransition = {
                MyAnimationManager.fadeAnimation.exit
            },
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
                    navController,
                    this@SharedTransitionLayout,
                    this@composable,
                    boundsTransform,
                ) {
                    println("READY BACK" + navController.allRouteStack())
                    navController.popBackStack()
                }
            }
            for(i in 1..31) {
                composable((ScreenRoute.ModuleScreen.route + "R" + i)) {
                    DetailScreenR(
                        vmUI,
                        navController,
                        i,
                        this@SharedTransitionLayout,
                        this@composable,
                        boundsTransform,
                    )
                }
            }
            for(i in 1..31) {
                (ScreenRoute.ModuleScreen.route + i).let { route ->
                    composable(route) {
                        DetailScreen(
                            vmUI,
                            navController,
                            route,
                            this@SharedTransitionLayout,
                            this@composable,
                            boundsTransform,
                        ) {
                            println("READY BACK" + navController.allRouteStack())
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}
