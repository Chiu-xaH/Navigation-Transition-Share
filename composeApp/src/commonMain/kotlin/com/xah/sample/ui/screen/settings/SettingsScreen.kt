package com.xah.sample.ui.screen.settings

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.animation
import animationsample.composeapp.generated.resources.blur_off
import animationsample.composeapp.generated.resources.blur_on
import animationsample.composeapp.generated.resources.close
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.settings
import animationsample.composeapp.generated.resources.swipe_left
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.logic.util.CAN_MOTION_BLUR
import com.xah.sample.ui.component.DividerTextExpandedWith
import com.xah.sample.ui.component.TransplantListItem
import com.xah.sample.ui.style.TransitionState
import com.xah.sample.ui.style.TransitionState.currentRoute
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.sample.ui.style.transitionBackground2
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.ui.util.MyAnimationManager.ANIMATION_SPEED
import com.xah.sample.ui.util.isCurrentRoute
import com.xah.sample.ui.util.isInBottom
import com.xah.sample.ui.util.navigateAndSaveForTransition
import com.xah.sample.ui.util.previousRoute
import com.xah.sample.viewmodel.UIViewModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    vm : UIViewModel,
//    showSurface : Boolean,
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
    onBackPressed: () -> Unit
) {
    val route = remember { ScreenRoute.SettingsScreen.route }
    val motionBlur by remember { derivedStateOf { vm.motionBlur } }
    val forceAnimation by remember { derivedStateOf { vm.forceAnimation } }
    val isCenterAnimation by remember { derivedStateOf { vm.isCenterAnimation } }
    val animationSpeed by remember { derivedStateOf { vm.animationSpeed } }

    LaunchedEffect(isCenterAnimation) {
        if(!isCenterAnimation) {
            vm.animationSpeed = MyAnimationManager.DEFAULT_ANIMATION_SPEED.toFloat()
            MyAnimationManager.ANIMATION_SPEED = MyAnimationManager.DEFAULT_ANIMATION_SPEED
        }
    }

    with(sharedTransitionScope) {
        CustomScaffold(
//            showSurface = vm.showSurface,
            route = route,
            navHostController = navController,
            modifier =
                transitionBackground2(navController, ScreenRoute.SettingsScreen.route,vm)
                .fillMaxSize()
                .sharedBounds(
                    boundsTransform = boundsTransform,
                    enter = MyAnimationManager.fadeAnimation.enter,
                    exit = MyAnimationManager.fadeAnimation.exit,
                    sharedContentState = rememberSharedContentState(key = "container_$route"),
                    animatedVisibilityScope = animatedContentScope,
                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                )
//                .skipToLookaheadSize()
            ,
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
                                    boundsTransform = boundsTransform,
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
                        headlineContent = { Text(text = "动画曲线") },
                        supportingContent = {
                            Row {
                                FilterChip(
                                    onClick = { vm.isCenterAnimation = true },
                                    label = { Text(text = "向中间运动") }, selected = isCenterAnimation
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                FilterChip(
                                    onClick = { vm.isCenterAnimation = false },
                                    label = { Text(text = "直接展开") }, selected = !isCenterAnimation
                                )
                            }
                        },
                        leadingContent = { Icon(painterResource(Res.drawable.deployed_code), null) },
                        modifier = Modifier.clickable { vm.isCenterAnimation = !isCenterAnimation }
                    )
                    TransplantListItem(
                        headlineContent = { Text(text = "动画时长 ${animationSpeed.toInt()}ms") },
                        supportingContent = {
                            Slider(
                                enabled = isCenterAnimation,
                                value = animationSpeed,
                                onValueChange = {
                                    vm.animationSpeed = it
                                    MyAnimationManager.ANIMATION_SPEED = it.toInt()
                                },
                                colors = SliderDefaults.colors(
                                    thumbColor = MaterialTheme.colorScheme.secondary,
                                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                                ),
                                steps = MyAnimationManager.DEFAULT_ANIMATION_SPEED*4-1,
                                valueRange = 0f..MyAnimationManager.DEFAULT_ANIMATION_SPEED*4f,
                                modifier = Modifier.padding(horizontal = 25.dp)
                            )
                        },
                        leadingContent = { Icon(painterResource(Res.drawable.deployed_code), null) },
                        modifier = Modifier.clickable {  }
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
                DividerTextExpandedWith("三级界面",vm) {
                    with(sharedTransitionScope) {

                        val r = ScreenRoute.Module31Screen.route
                        ListItem(
                            modifier = Modifier
                                .sharedBounds(
                                    boundsTransform = boundsTransform,
                                    enter = MyAnimationManager.fadeAnimation.enter,
                                    exit = MyAnimationManager.fadeAnimation.exit,
                                    sharedContentState = rememberSharedContentState(key = "container_$r"),
                                    animatedVisibilityScope = animatedContentScope,
                                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                                )
                                .clickable {
                                    navController.navigateAndSaveForTransition(r)
                                },
                            headlineContent = { Text(r) },
                            leadingContent = {
                                Icon(
                                    painterResource(Res.drawable.deployed_code),
                                    null,
                                    modifier = Modifier.sharedElement(
                                        boundsTransform = boundsTransform,
                                        sharedContentState = rememberSharedContentState(key = "title_$r"),
                                        animatedVisibilityScope = animatedContentScope,
                                    )
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
}


@Composable
fun CustomScaffold(
//    showSurface : Boolean,
    route: String,
    navHostController : NavController,
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable (() -> Unit) = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor : Color? = null,
    content: @Composable ((PaddingValues) -> Unit)
) {
    // 当从CustomScaffold1向CustomScaffold2时，CustomScaffold2先showSurface=false再true，而CustomScaffold1一直为true
    val isCurrentEntry = navHostController.isCurrentRoute(route)
//            || navHostController.previousRoute() == route
    val isPreviousEntry = navHostController.previousRoute() == route
    // 当回退时，即从CustomScaffold2回CustomScaffold1时，CustomScaffold2立刻showSurface=false，而CustomScaffold1一直为true
    var show by rememberSaveable(route) { mutableStateOf(false) }

    LaunchedEffect(isCurrentEntry) {
        // 当前页面首次进入时播放动画
        if (isCurrentEntry && !show) {
            show = false
            delay(ANIMATION_SPEED* 1L)
            show = true
        } else if(show) {
            if(navHostController.isInBottom(route)) {
                return@LaunchedEffect
            }
            show = false
        }
    }

// 回退后恢复上一个页面的显示状态
    LaunchedEffect(isPreviousEntry) {
        if (isPreviousEntry) {
            show = true
        }
    }
    LaunchedEffect(isCurrentEntry,isPreviousEntry) {
//        if(isCurrentEntry && !isPreviousEntry) {
            // 有两种可能
            // 当前布局收起时父布局 应该保持show=true
            // 当前布局展开时当前布局 应该延时显示
            // 如何判断呢？
//        } else if(!isCurrentEntry && isCurrentEntry) {
            // 有两种可能
            // 当前布局收起时当前布局 应该立刻show=false
            // 当前布局展开时父布局 应该保持show=true
//        }
//        if(!isCurrentEntry && !isPreviousEntry) {
//            show = false
//        }
        println("route $route | current $isCurrentEntry| previous $isPreviousEntry")
//        if(isCurrentEntry) {
//            if(show) {
//                return@LaunchedEffect
//            }
//            if(!show) {
//                show = false
//                delay(ANIMATION_SPEED *1L)
//                show = true
//            } else {
//                show = false
//            }
//        }
//        if(isPreviousEntry) {
//            show = true
//        }
//        if(isCurrentEntry || isPreviousEntry) {
//            show = false
//            delay(ANIMATION_SPEED *1L)
//            show = true
//        } else {
//            show = false
//        }
    }

    Scaffold(
        containerColor = containerColor ?: if(TransitionState.transplantBackground) Color.Transparent else MaterialTheme.colorScheme.surface,
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
    ) { innerPadding ->
        AnimatedVisibility(
            visible = show,
            enter  = if(ANIMATION_SPEED == 0) fadeIn(tween(durationMillis = 0)) else fadeIn(),
            exit = fadeOut(tween(durationMillis = 0))
        ) {
            content(innerPadding)
        }
    }
}