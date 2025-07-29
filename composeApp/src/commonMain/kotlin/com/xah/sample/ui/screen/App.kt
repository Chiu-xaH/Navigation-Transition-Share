package com.xah.sample.ui.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.screen.detail.DetailScreen
import com.xah.sample.ui.screen.detail.DetailScreenR
import com.xah.sample.ui.screen.home.HomeScreen
import com.xah.sample.ui.screen.settings.SettingsScreen
import com.xah.transition.style.DefaultTransitionStyle
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

    SharedTransitionLayout(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        NavHost(
            navController = navController,
            startDestination = firstRoute,
            enterTransition = {
                DefaultTransitionStyle.fadeAnimation.enter
            },
            exitTransition = {
                DefaultTransitionStyle.fadeAnimation.exit
            },
        ) {
            composable(firstRoute) {
                HomeScreen(
                    navController,
                    this@SharedTransitionLayout,
                    this@composable,
                ) {
                    navController.navigateAndSaveForTransition(it)
                }
            }
            composable(ScreenRoute.SettingsScreen.route) {
                SettingsScreen(
                    navController,
                    this@SharedTransitionLayout,
                    this@composable,
                )
            }
            for(i in 1..31) {
                composable((ScreenRoute.ModuleScreen.route + "R" + i)) {
                    DetailScreenR(
                        navController,
                        i,
                        this@SharedTransitionLayout,
                        this@composable,
                    )
                }
            }
            for(i in 1..31) {
                (ScreenRoute.ModuleScreen.route + i).let { route ->
                    composable(route) {
                        DetailScreen(
                            navController,
                            route,
                            this@SharedTransitionLayout,
                            this@composable,
                        )
                    }
                }
            }
        }
    }
}
