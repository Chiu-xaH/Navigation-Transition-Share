package com.xah.sample.ui.screen

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.xah.transition.component.transitionComposable
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.screen.detail.DetailScreen
import com.xah.sample.ui.screen.detail.DetailScreenR
import com.xah.sample.ui.screen.home.HomeScreen
import com.xah.sample.ui.screen.settings.SettingsScreen
import com.xah.transition.component.TransitionNavHost
import com.xah.transition.util.navigateAndSaveForTransition
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
//    val vm = remember { UIViewModel() }
    MaterialTheme {
        AppNavHost()
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val firstRoute = remember { ScreenRoute.HomeScreen.route }
    TransitionNavHost (
        navController = navController,
        startDestination = firstRoute,
//        enterTransition = {
//                fadeIn(
//                    animationSpec = tween(durationMillis = TransitionState.curveStyle.speedMs ),
//                )
//        },
//        exitTransition = {
//                fadeOut(
//                    animationSpec = tween(durationMillis = TransitionState.curveStyle.speedMs ),
//                )
//        },
    ) {
        transitionComposable(firstRoute) {
            HomeScreen(
                navController,
            ) {
                navController.navigateAndSaveForTransition(it)
            }
        }
        transitionComposable(ScreenRoute.SettingsScreen.route) {
            SettingsScreen(
                navController,
            )
        }
        for(i in 1..31) {
            transitionComposable((ScreenRoute.ModuleScreen.route + "R" + i)) {
                DetailScreenR(
                    navController,
                    i,
                )
            }
        }
        for(i in 1..31) {
            (ScreenRoute.ModuleScreen.route + i).let { route ->
                transitionComposable(route) {
                    DetailScreen(
                        navController,
                        route,
                    )
                }
            }
        }
    }
}
