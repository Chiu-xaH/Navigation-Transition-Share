package com.xah.sample.ui.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.xah.sample.ui.style.TransitionState

fun NavController.navigateAndClear(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId) { inclusive = true } // 清除所有历史记录
        launchSingleTop = true // 避免多次实例化相同的目的地
    }
}

fun NavController.navigateAndSave(route: String) {
    navigate(route) {
//        popUpTo(graph.startDestinationId) {
//            saveState = true
//        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateAndSaveForTransition(route: String, transplantBackground : Boolean = false) {
    TransitionState.transplantBackground = transplantBackground
    println("READY GO $route | CURRENT" + this.allRouteStack())
    navigateAndSave(route)
    println("GONE" + this.allRouteStack())
}

@Composable
fun NavController.currentRoute() : String? = this.currentBackStackEntryAsState().value?.destination?.route
@Composable
fun NavController.isCurrentRoute(route: String) : Boolean = currentRoute() == route

// 得到上一级
fun NavController.previousRoute(): String? = this.previousBackStackEntry?.destination?.route
// 所有
fun NavController.allRouteStack() : List<String> = this.currentBackStack.value.mapNotNull { it.destination.route }

//@Composable
//fun rememberIsPreviousHome(navController: NavController): Boolean {
//   val d = navController.currentBackStack.collectAsState(initial = "")
//}

