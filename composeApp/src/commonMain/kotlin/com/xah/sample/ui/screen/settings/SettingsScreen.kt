package com.xah.sample.ui.screen.settings

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.animation
import animationsample.composeapp.generated.resources.blur_off
import animationsample.composeapp.generated.resources.blur_on
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.settings
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.logic.util.CAN_MOTION_BLUR
import com.xah.sample.ui.component.APP_HORIZONTAL_DP
import com.xah.sample.ui.component.CustomSlider
import com.xah.sample.ui.component.DividerTextExpandedWith
import com.xah.sample.ui.component.TransplantListItem
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.transition.component.TopBarNavigateIcon
import com.xah.transition.component.TransitionScaffold
import com.xah.transition.component.containerShare
import com.xah.transition.component.iconElementShare
import com.xah.transition.state.TransitionState
import com.xah.transition.style.TransitionLevel
import com.xah.transition.util.navigateAndSaveForTransition
import org.jetbrains.compose.resources.painterResource

private val transitionLevels = TransitionLevel.entries

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val route = remember { ScreenRoute.SettingsScreen.route }
    var motionBlur by remember { mutableStateOf(TransitionState.transitionBackgroundStyle.motionBlur) }
    LaunchedEffect(motionBlur) {
        TransitionState.transitionBackgroundStyle.motionBlur = motionBlur
    }

    var transition by remember { mutableStateOf(TransitionState.transitionBackgroundStyle.level) }
    LaunchedEffect(transition) {
         TransitionState.transitionBackgroundStyle.level = transition
    }


    with(sharedTransitionScope) {
        TransitionScaffold(
            route = route,
            navHostController = navController,
            animatedContentScope = animatedContentScope,
            topBar = {
                TopAppBar(
                    title = { Text("设置") },
                    navigationIcon = {
                        TopBarNavigateIcon(navController,animatedContentScope,route, Res.drawable.settings)
                    },
                    colors = topBarTransplantColor(),
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
            ){
                DividerTextExpandedWith("模糊") {
                    TransplantListItem(
                        headlineContent = { Text("运动模糊") },
                        leadingContent = {
                            Icon(painterResource(if(motionBlur) Res.drawable.blur_on else Res.drawable.blur_off),null)
                        },
                        trailingContent = {
                            Switch(enabled = CAN_MOTION_BLUR, checked = motionBlur, onCheckedChange = { motionBlur = !motionBlur })
                        },
                        supportingContent = { Text("一些组件在运动中会伴随模糊效果" + if(CAN_MOTION_BLUR) "(Android 12+)" else "")},
                        modifier = Modifier.clickable { motionBlur = !motionBlur }
                    )
                }
                DividerTextExpandedWith("动效") {
                    TransplantListItem(
                        headlineContent = {
                            Column {
                                Text(text = "转场动画等级")
                                Text("Level${transition.code + 1} (${transition.title})")
                            }
                        },
                        supportingContent = {
                            Text(text = "转场动画伴随较高强度的模糊、缩放、压暗、回弹等效果，等级越高，越可能会在某些设备上掉帧")
                        },
                        leadingContent = { Icon(painterResource(Res.drawable.animation), contentDescription = "Localized description",) },
                    )
                    CustomSlider(
                        value = transition.code.toFloat(),
                        onValueChange = { value ->
                            val level = transitionLevels.find { it.code == value.toInt() }
                            if(level != null) {
                                transition = level
                            }
                        },
                        steps = 2,
                        modifier = Modifier.padding(bottom = APP_HORIZONTAL_DP),
                        valueRange = 0f..3f,
                    )

                }

                DividerTextExpandedWith("三级界面") {
                    val r = ScreenRoute.Module31Screen.route
                    TransplantListItem(
                        modifier = Modifier.containerShare(sharedTransitionScope,animatedContentScope=animatedContentScope,route=r)
                            .clickable {
                                navController.navigateAndSaveForTransition(r)
                            },
                        headlineContent = { Text(r) },
                        leadingContent = {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                modifier = Modifier.iconElementShare(sharedTransitionScope,animatedContentScope=animatedContentScope, route = r)
                            )
                        },
                        supportingContent = {
                            Text("内容")
                        }
                    )
                }
            }
        }
    }
}


