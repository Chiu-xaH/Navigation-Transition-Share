package com.xah.sample.ui.screen.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.deployed_code_filled
import com.xah.sample.logic.model.ui.HomeScreenRoute
import com.xah.sample.logic.model.ui.NavigationItem
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.screen.home.screen.IconSampleScreen
import com.xah.sample.ui.screen.home.screen.RSampleScreen
import com.xah.sample.ui.screen.home.screen.SingleColumnSampleScreen
import com.xah.sample.ui.screen.home.screen.TwoColumnSampleScreen
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.transition.style.transitionBackground
import com.xah.transition.util.navigateAndSaveForTransition
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController : NavHostController,
    onItemClick: (String) -> Unit,
) {
    val barItems = listOf(
        NavigationItem(
            HomeScreenRoute.TwoColumnSampleScreen.route,
            "示例1",
            painterResource(Res.drawable.deployed_code),
            painterResource(Res.drawable.deployed_code_filled)
        ),
        NavigationItem(
            HomeScreenRoute.SingleColumnSampleScreen.route,
            "示例2",
            painterResource(Res.drawable.deployed_code),
            painterResource(Res.drawable.deployed_code_filled)
        ),
        NavigationItem(
            HomeScreenRoute.IconSampleScreen.route,
            "示例3",
            painterResource(Res.drawable.deployed_code),
            painterResource(Res.drawable.deployed_code_filled)
        ),
        NavigationItem(
            HomeScreenRoute.RSampleScreen.route,
            "示例4",
            painterResource(Res.drawable.deployed_code),
            painterResource(Res.drawable.deployed_code_filled)
        ),
    )
    val navControllerHome = rememberNavController()
    val firstScreen = remember { HomeScreenRoute.TwoColumnSampleScreen.route }
    var selectedItem by rememberSaveable { mutableStateOf(firstScreen) }

    NavigationSuiteScaffold(
        modifier =
//            if(firstStart) Modifier else
            Modifier.
            transitionBackground(navController, ScreenRoute.HomeScreen.route),
        navigationSuiteItems =  {
            barItems.forEach { item ->
                val route = item.route
                val selected = route == selectedItem
                item(
                    selected = selected,
                    onClick = {
                        selectedItem = route
                        if (!selected) { navControllerHome.navigateAndSaveForTransition(route) }
                    },
                    label = { Text(text = item.label) },
                    icon = {
                        Icon(if(selected)item.filledIcon else item.icon, contentDescription = item.label)
                    }
                )
            }
        }
    ) {
        NavHost(
            navController = navControllerHome,
            startDestination = firstScreen,
            enterTransition = { MyAnimationManager.getCenterAnimation().enter },
            exitTransition = { MyAnimationManager.getCenterAnimation().exit }
        ) {
            composable(HomeScreenRoute.TwoColumnSampleScreen.route) {
                TwoColumnSampleScreen(navController,onItemClick)
            }
            composable(HomeScreenRoute.SingleColumnSampleScreen.route) {
                SingleColumnSampleScreen(navController,onItemClick)
            }
            composable(HomeScreenRoute.IconSampleScreen.route) {
                IconSampleScreen(navController)
            }
            composable(HomeScreenRoute.RSampleScreen.route) {
                RSampleScreen(navController,onItemClick)
            }
        }
    }
}




