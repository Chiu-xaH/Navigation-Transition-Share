package com.xah.sample.ui.screen.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.close
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.CustomScaffold
import com.xah.sample.ui.component.StyleCardListItem
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.sample.ui.style.transitionBackground
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.ui.util.navigateAndSaveForTransition
import com.xah.sample.viewmodel.UIViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    route : String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
    onBackPressed: () -> Unit
) {
    with(sharedTransitionScope) {
        CustomScaffold (
            route = route,
            navHostController = navHostController,
            modifier = Modifier
                .fillMaxSize()
                .sharedBounds(
                    boundsTransform = boundsTransform,
                    enter = MyAnimationManager.fadeAnimation.enter,
                    exit = MyAnimationManager.fadeAnimation.exit,
                    sharedContentState = rememberSharedContentState(key = "container_$route"),
                    animatedVisibilityScope = animatedContentScope,
                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                )
//                .clickable {
//                    onBackPressed()
//                },
                ,
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.sharedElement(
                                    boundsTransform = boundsTransform,
                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                            )
                        }
                    },
                    title = { Text(route) },
                    colors = topBarTransplantColor(),
                    actions = {
                        IconButton (onClick = onBackPressed) {
                            Icon(
                                painterResource(Res.drawable.close),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                                )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                Button (onClick = onBackPressed, modifier = Modifier.align(Alignment.Center)) {
                    Text("界面 $route")
                }
            }
        }
    }
}

// 递归
@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenR(
    vm: UIViewModel,
    navHostController: NavHostController,
    routeI : Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
) {
    val onBackPressed = {
        navHostController.popBackStack()
    }
    val route = ScreenRoute.ModuleScreen.route + "R"+ routeI
    with(sharedTransitionScope) {
        CustomScaffold (
            route = route,
            navHostController = navHostController,
            modifier =  transitionBackground(navHostController, route,vm)
                .fillMaxSize()
                .sharedBounds(
                    boundsTransform = boundsTransform,
                    enter = MyAnimationManager.fadeAnimation.enter,
                    exit = MyAnimationManager.fadeAnimation.exit,
                    sharedContentState = rememberSharedContentState(key = "container_$route"),
                    animatedVisibilityScope = animatedContentScope,
                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                )
            ,
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {onBackPressed()}) {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.sharedElement(
                                    boundsTransform = boundsTransform,
                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                            )
                        }
                    },
                    title = { Text(route) },
                    colors = topBarTransplantColor(),
                    actions = {
                        IconButton (onClick = { onBackPressed() }) {
                            Icon(
                                painterResource(Res.drawable.close),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
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
                            Icon(painterResource(Res.drawable.deployed_code),null, modifier = Modifier.sharedElement(
                                boundsTransform = boundsTransform,
                                sharedContentState = rememberSharedContentState(key = "title_$newRoute"),
                                animatedVisibilityScope = animatedContentScope,
                            ))
                        },
                        cardModifier =  Modifier.align(Alignment.Center).sharedBounds(
                            boundsTransform = boundsTransform,
                            enter = MyAnimationManager.fadeAnimation.enter,
                            exit = MyAnimationManager.fadeAnimation.exit,
                            sharedContentState = rememberSharedContentState(key = "container_$newRoute"),
                            animatedVisibilityScope = animatedContentScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        ),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.clickable {
                            navHostController.navigateAndSaveForTransition(newRoute)
                        }
                    )
                } else {
                    Button(onClick = { onBackPressed()}, modifier = Modifier.align(Alignment.Center)) {
                        Text("返回")
                    }
                }
            }
        }
    }
}