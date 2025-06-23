package com.xah.sample.ui.screen.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.deployed_code_filled
import com.xah.sample.logic.model.ui.HomeScreenRoute
import com.xah.sample.logic.model.ui.NavigationItem
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.screen.detail.DetailScreenR
import com.xah.sample.ui.screen.home.screen.IconSampleScreen
import com.xah.sample.ui.screen.home.screen.RSampleScreen
import com.xah.sample.ui.screen.home.screen.SingleColumnSampleScreen
import com.xah.sample.ui.screen.home.screen.TwoColumnSampleScreen
import com.xah.sample.ui.screen.settings.CustomScaffold
import com.xah.sample.ui.style.transitionBackground
import com.xah.sample.ui.style.transitionBackground2
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.ui.util.allRouteStack
import com.xah.sample.ui.util.currentRoute
import com.xah.sample.ui.util.isCurrentRoute
import com.xah.sample.ui.util.navigateAndSaveForTransition
import com.xah.sample.ui.util.previousRoute
import com.xah.sample.viewmodel.UIViewModel
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: UIViewModel,
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
    onItemClick: (String) -> Unit,
) {
//    if(vm.firstStart) {
//        LaunchedEffect(Unit) {
//            vm.firstStart = false
//        }
//    }

//    vm.isExpandedScreen = !navController.isCurrentRoute(ScreenRoute.HomeScreen.route)



//    val isExpanded by remember { derivedStateOf { vm.isExpandedScreen } }
//    val firstStart by remember { derivedStateOf { vm.firstStart } }






    // 每次导航栈变化都会触发 recomposition
//    val isExpanded = remember(navBackStackEntry) {
//        navController.previousBackStackEntry?.destination?.route == ScreenRoute.HomeScreen.route
//    }

//    var isExpanded2 = navController.previousRoute() == ScreenRoute.HomeScreen.route
//

//    println(isExpanded)

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
            transitionBackground2(navController, ScreenRoute.HomeScreen.route,vm),
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
            enterTransition = { MyAnimationManager.centerAnimation.enter },
            exitTransition = { MyAnimationManager.centerAnimation.exit }
        ) {
            composable(HomeScreenRoute.TwoColumnSampleScreen.route) {
                TwoColumnSampleScreen(vm,navController,sharedTransitionScope,animatedContentScope,boundsTransform,onItemClick)
            }
            composable(HomeScreenRoute.SingleColumnSampleScreen.route) {
                SingleColumnSampleScreen(vm,navController,sharedTransitionScope,animatedContentScope,boundsTransform,onItemClick)
            }
            composable(HomeScreenRoute.IconSampleScreen.route) {
                IconSampleScreen(vm,navController,sharedTransitionScope,animatedContentScope,boundsTransform,onItemClick)
            }
            composable(HomeScreenRoute.RSampleScreen.route) {
                RSampleScreen(vm,navController,sharedTransitionScope,animatedContentScope,boundsTransform,onItemClick)
            }
        }
    }
}




