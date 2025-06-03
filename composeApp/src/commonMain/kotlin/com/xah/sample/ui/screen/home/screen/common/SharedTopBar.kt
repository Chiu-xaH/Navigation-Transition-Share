package com.xah.sample.ui.screen.home.screen.common

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.settings
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.style.topBarStyle
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.ui.util.navigateAndSaveForTransition
import com.xah.sample.viewmodel.UIViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTopBar(
    vmUI : UIViewModel,
    title : String,
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
    content : @Composable ((PaddingValues) -> Unit)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(title) },
                actions = {
                    with(sharedTransitionScope) {
                        val route = remember { ScreenRoute.SettingsScreen.route }
                        IconButton(
                            onClick = {
                                navController.navigateAndSaveForTransition(route,true)
                            },
                            modifier = Modifier.sharedBounds(
                                boundsTransform = boundsTransform,
                                enter = MyAnimationManager.fadeAnimation.enter,
                                exit = MyAnimationManager.fadeAnimation.exit,
                                sharedContentState = rememberSharedContentState(key = "container_$route"),
                                animatedVisibilityScope = animatedContentScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            )
                        ) {
                            Icon(
                                painterResource(Res.drawable.settings),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.sharedElement(
                                    boundsTransform = boundsTransform,
                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                            )
                        }
                    }
                },
                colors = topBarTransplantColor(),
                modifier = Modifier.topBarStyle()
            )
        },
        content = content
    )
}