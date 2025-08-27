package com.xah.sample.ui.screen.home.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.APP_HORIZONTAL_DP
import com.xah.sample.ui.component.DividerTextExpandedWith
import com.xah.sample.ui.screen.home.screen.common.SharedTopBar
import com.xah.sample.ui.style.RowHorizontal
import com.xah.sample.viewmodel.UIViewModel
import com.xah.transition.component.containerShare
import com.xah.transition.component.iconElementShare
import com.xah.transition.util.navigateAndSaveForTransition
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun IconSampleScreen(
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val func2 = remember {
        listOf(
            ScreenRoute.Module11Screen.route,
            ScreenRoute.Module12Screen.route,
            ScreenRoute.Module13Screen.route,
            ScreenRoute.Module14Screen.route,
            ScreenRoute.Module15Screen.route,
            ScreenRoute.Module16Screen.route,
            ScreenRoute.Module17Screen.route,
            ScreenRoute.Module18Screen.route,
            ScreenRoute.Module19Screen.route,
            ScreenRoute.Module20Screen.route,
        )
    }

    val scrollState = rememberScrollState()

    SharedTopBar(
        title = "图标与按钮",
        navController = navController,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
    ) { innerPadding ->
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Spacer(Modifier.height(innerPadding.calculateTopPadding()).statusBarsPadding())
            DividerTextExpandedWith("无边框按钮") {
                RowHorizontal(modifier = Modifier.fillMaxWidth()) {
                    val route = func2[0]
                    IconButton(
                        onClick = {
                            navController.navigateAndSaveForTransition(route,true)
                        },
                        modifier = Modifier.containerShare(sharedTransitionScope,animatedContentScope = animatedContentScope, route = route, resize = true)
                    ) {
                        Icon(
                            painterResource(Res.drawable.deployed_code),
                            null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.iconElementShare(sharedTransitionScope,animatedContentScope=animatedContentScope, route = route)
                        )
                    }
                    Spacer(Modifier.width(APP_HORIZONTAL_DP))
                    with(sharedTransitionScope) {
                        val route = func2[1]
                        TextButton(
                            onClick = {
                                navController.navigateAndSaveForTransition(route,true)
                            },
                            modifier = Modifier.containerShare(sharedTransitionScope,animatedContentScope=animatedContentScope, route = route)
                        ) {
                            Text("按钮")
                        }
                    }
                }
            }
            DividerTextExpandedWith("有边框按钮") {
                RowHorizontal(modifier = Modifier.fillMaxWidth())  {
                    val route = func2[5]
                    FloatingActionButton (
                        elevation =  FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                        onClick = {
                            navController.navigateAndSaveForTransition(route)
                        },
                        modifier = Modifier.containerShare(sharedTransitionScope,animatedContentScope=animatedContentScope,route=route)
                    ) {
                        Icon(
                            painterResource(Res.drawable.deployed_code),
                            null,
                            modifier = Modifier.iconElementShare(sharedTransitionScope,animatedContentScope=animatedContentScope,  route = route)
                        )
                    }
                    Spacer(Modifier.width(APP_HORIZONTAL_DP))
                    val route2 = func2[6]
                    FilledTonalIconButton (
                        onClick = {
                            navController.navigateAndSaveForTransition(route2)
                        },
                        modifier = Modifier.containerShare(sharedTransitionScope,animatedContentScope=animatedContentScope,route=route2, roundShape = CircleShape)
                    ) {
                        Icon(
                            painterResource(Res.drawable.deployed_code),
                            null,
                            modifier = Modifier.iconElementShare(sharedTransitionScope,animatedContentScope=animatedContentScope,  route = route2)
                        )
                    }
                    Spacer(Modifier.width(APP_HORIZONTAL_DP))
                    val route3 = func2[4]
                    FilledTonalIconButton (
                        onClick = {
                            navController.navigateAndSaveForTransition(route3,true)
                        },
                    ) {
                        Icon(
                            painterResource(Res.drawable.deployed_code),
                            null,
                            modifier = Modifier.iconElementShare(sharedTransitionScope,animatedContentScope=animatedContentScope,  route = route3,)
                        )
                    }
                }
            }
            Spacer(Modifier.height(innerPadding.calculateBottomPadding() + APP_HORIZONTAL_DP).navigationBarsPadding())
        }
    }
}