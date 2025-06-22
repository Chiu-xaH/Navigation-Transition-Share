package com.xah.sample.ui.screen.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.close
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.ui.screen.settings.CustomScaffold
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.viewmodel.UIViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    vm : UIViewModel,
    navHostController: NavHostController,
//    showSurface : Boolean,
    route : String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
    onBackPressed: () -> Unit
) {
    with(sharedTransitionScope) {
        CustomScaffold (
//            containerColor = MaterialTheme.colorScheme,
//            showSurface = vm.showSurface,
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