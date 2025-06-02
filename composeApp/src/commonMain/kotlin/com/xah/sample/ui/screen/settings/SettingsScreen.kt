package com.xah.sample.ui.screen.settings

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.animation
import animationsample.composeapp.generated.resources.blur_off
import animationsample.composeapp.generated.resources.blur_on
import animationsample.composeapp.generated.resources.close
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.settings
import animationsample.composeapp.generated.resources.swipe_left
import animationsample.composeapp.generated.resources.texture
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.logic.util.CAN_MOTION_BLUR
import com.xah.sample.ui.component.DividerTextExpandedWith
import com.xah.sample.ui.component.TransplantListItem
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.viewmodel.UIViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    vm : UIViewModel,
    showSurface : Boolean,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBackPressed: () -> Unit
) {
    val route = remember { ScreenRoute.SettingsScreen.route }
    val motionBlur by remember { derivedStateOf { vm.motionBlur } }
    val forceAnimation by remember { derivedStateOf { vm.forceAnimation } }
    val transplantBackground by remember { derivedStateOf { vm.transplantBackground } }

    with(sharedTransitionScope) {
        CustomScaffold(
            vm = vm,
            showSurface = showSurface,
//            containerColor = if(transplantBackground) Color.Transparent else MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxSize()
                .sharedBounds(
                    enter = fadeIn(tween(durationMillis = MyAnimationManager.ANIMATION_SPEED)),
                    exit = fadeOut(tween(durationMillis = MyAnimationManager.ANIMATION_SPEED)),
                    sharedContentState = rememberSharedContentState(key = "container_$route"),
                    animatedVisibilityScope = animatedContentScope,
                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                ),
            topBar = {
                TopAppBar(
                    title = { Text("设置") },
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                painterResource(Res.drawable.settings),
                                null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.sharedElement(
                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                            )
                        }
                    },
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
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
            ){
                DividerTextExpandedWith("模糊",vm) {
                    TransplantListItem(
                        headlineContent = { Text("运动模糊") },
                        leadingContent = {
                            Icon(painterResource(if(motionBlur) Res.drawable.blur_on else Res.drawable.blur_off),null)
                        },
                        trailingContent = {
                            Switch(enabled = CAN_MOTION_BLUR, checked = motionBlur, onCheckedChange = { vm.motionBlur = !motionBlur })
                        },
                        supportingContent = { Text("一些组件在运动中会伴随模糊效果" + if(CAN_MOTION_BLUR) "(Android 12+)" else "")},
                        modifier = Modifier.clickable { vm.motionBlur = !motionBlur }
                    )
                }
                DividerTextExpandedWith("动效",vm) {
                    TransplantListItem(
                        headlineContent = { Text("增强转场动画") },
                        leadingContent = {
                            Icon(painterResource(Res.drawable.animation),null)
                        },
                        trailingContent = {
                            Switch(checked = forceAnimation, onCheckedChange = { vm.forceAnimation = !forceAnimation })
                        },
                        supportingContent = { Text("转场时启用背景模糊和缩放")},
                        modifier = Modifier.clickable { vm.forceAnimation = !forceAnimation }
                    )
                    TransplantListItem(
                        headlineContent = { Text("透明背景") },
                        leadingContent = {
                            Icon(painterResource(Res.drawable.texture),null)
                        },
                        trailingContent = {
                            Switch(checked = transplantBackground, onCheckedChange = { vm.transplantBackground = !transplantBackground })
                        },
                        supportingContent = { Text("转场时两者背景透明过渡")},
                        modifier = Modifier.clickable { vm.transplantBackground = !transplantBackground }
                    )
                }
                DividerTextExpandedWith("其它",vm) {
                    TransplantListItem(
                        headlineContent = { Text("预测式返回") },
                        leadingContent = {
                            Icon(painterResource(Res.drawable.swipe_left),null)
                        },
                        trailingContent = {
                            Switch(enabled = false, checked = false, onCheckedChange = { })
                        },
                        supportingContent = { Text("因和此页面某些组件测量冲突而关闭，需打开请自行修改清单文件")},
                        modifier = Modifier.clickable { }
                    )
                }
            }
        }
    }
}


@Composable
fun CustomScaffold(
    vm : UIViewModel,
    showSurface : Boolean,
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable (() -> Unit) = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition. End,
    content: @Composable ((PaddingValues) -> Unit)
) {
    val transplantBackground by remember { derivedStateOf { vm.transplantBackground } }

    Scaffold(
        containerColor = if(transplantBackground) Color.Transparent else MaterialTheme.colorScheme.surface,
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
    ) { innerPadding ->
        if(showSurface) {
            AnimatedVisibility(
                visible = true,
                enter  = fadeIn(),
                exit = fadeOut()
            ) {
                content(innerPadding)
            }
        }
    }
}