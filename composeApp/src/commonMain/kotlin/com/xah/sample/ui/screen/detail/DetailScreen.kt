package com.xah.sample.ui.screen.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.StyleCardListItem
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.transition.component.TopBarNavigateIcon
import com.xah.transition.component.TransitionScaffold
import com.xah.transition.component.containerShare
import com.xah.transition.component.iconElementShare
import com.xah.transition.util.navigateAndSaveForTransition
import com.xah.transition.util.popBackStackForTransition
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    route : String,
) {
    TransitionScaffold (
//            roundShape = if(route == ScreenRoute.Module17Screen.route) CircleShape else MaterialTheme.shapes.medium,
        route = route,
        navHostController = navHostController,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    TopBarNavigateIcon(navHostController,route, painterResource(Res.drawable.deployed_code))
                },
                title = { Text(
                    route,
//                        modifier = titleElementShare(animatedContentScope=animatedContentScope, boundsTransform = boundsTransform, route = route)
                ) },
                colors = topBarTransplantColor(),
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Button (onClick = { navHostController.popBackStackForTransition() }, modifier = Modifier.align(Alignment.Center)) {
                Text("界面 $route")
            }
        }
    }
}

// 递归
@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenR(
    navHostController: NavHostController,
    routeI : Int,
) {
    val route = ScreenRoute.ModuleScreen.route + "R"+ routeI
    TransitionScaffold (
        route = route,
        navHostController = navHostController,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    TopBarNavigateIcon(navHostController,route, painterResource(Res.drawable.deployed_code))
                },
                title = { Text(route) },
                colors = topBarTransplantColor(),
            )
        }
    ) { innerPadding ->
        Box (modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            val newRouteI = routeI + 1
            val newRoute = ScreenRoute.ModuleScreen.route + "R"+ newRouteI
            if(newRouteI < 31) {
                StyleCardListItem(
                    headlineContent = {
                        Text("界面 $newRouteI")
                    },
                    leadingContent = {
                        Icon(painterResource(Res.drawable.deployed_code),null,
                            modifier = Modifier.iconElementShare(route = newRoute)
                        )
                    },
                    cardModifier = Modifier.align(Alignment.Center).containerShare(newRoute),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.clickable {
                        navHostController.navigateAndSaveForTransition(newRoute)
                    }
                )
            } else {
                Button(onClick = { navHostController.popBackStackForTransition() }, modifier = Modifier.align(Alignment.Center)) {
                    Text("返回")
                }
            }
        }
    }
}