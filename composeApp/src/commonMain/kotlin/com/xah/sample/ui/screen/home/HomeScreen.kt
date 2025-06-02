package com.xah.sample.ui.screen.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.settings
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.SmallCard
import com.xah.sample.ui.component.TransplantListItem
import com.xah.sample.ui.screen.detail.pure.PureScreen
import com.xah.sample.ui.style.topBarStyle
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.sample.ui.style.transitionBackground
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.ui.util.isCurrentRoute
import com.xah.sample.ui.util.navigateAndSave
import com.xah.sample.viewmodel.UIViewModel
import dev.chrisbanes.haze.hazeSource
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: UIViewModel,
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onItemClick: (String) -> Unit,
) {
    if(vm.firstStart) {
        LaunchedEffect(Unit) {
            vm.firstStart = false
        }
    }

    vm.isExpandedScreen = !navController.isCurrentRoute(ScreenRoute.HomeScreen.route)


    // 用于回退时保存滑动位置
    var scrollState = rememberLazyGridState()
    val isExpanded by remember { derivedStateOf { vm.isExpandedScreen } }
    val firstStart by remember { derivedStateOf { vm.firstStart } }


    val func = remember {
        listOf<String>(
            ScreenRoute.Module1Screen.route,
            ScreenRoute.Module2Screen.route,
            ScreenRoute.Module3Screen.route,
            ScreenRoute.Module4Screen.route,
            ScreenRoute.Module5Screen.route,
            ScreenRoute.Module6Screen.route,
            ScreenRoute.Module7Screen.route,
            ScreenRoute.Module8Screen.route,
            ScreenRoute.Module9Screen.route,
            ScreenRoute.Module10Screen.route,
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
            ScreenRoute.Module21Screen.route,
            ScreenRoute.Module22Screen.route,
            ScreenRoute.Module23Screen.route,
            ScreenRoute.Module24Screen.route,
            ScreenRoute.Module25Screen.route,
            ScreenRoute.Module26Screen.route,
            ScreenRoute.Module27Screen.route,
            ScreenRoute.Module28Screen.route,
            ScreenRoute.Module29Screen.route,
            ScreenRoute.Module30Screen.route,
        )
    }
    val transplantBackground by remember { derivedStateOf { vm.transplantBackground } }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = if(firstStart) Modifier else transitionBackground(isExpanded,vm) .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = if(transplantBackground) Color.Transparent else MaterialTheme.colorScheme.surface,
        topBar = {
            MediumTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text("Animation Sample") },
                actions = {
                    with(sharedTransitionScope) {
                        val route = remember { ScreenRoute.SettingsScreen.route }
                        IconButton(
                            onClick = {
                                navController.navigateAndSave(route)
                            },
                            modifier = Modifier.sharedBounds(
                                enter = fadeIn(tween(durationMillis = MyAnimationManager.ANIMATION_SPEED)),
                                exit = fadeOut(tween(durationMillis = MyAnimationManager.ANIMATION_SPEED)),
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
        }
    ){ innerPadding ->
        LazyVerticalGrid(state = scrollState, columns = GridCells.Fixed(2),modifier = Modifier.padding(horizontal = 12.dp)) {
            items(2) {
                Spacer(Modifier.height(innerPadding.calculateTopPadding()).statusBarsPadding())
            }
            items(func.size, key = { it }) { index ->
                val route = func[index]
                with(sharedTransitionScope) {
                    SmallCard(
                        modifier = Modifier
                            .padding(horizontal = 3.dp, vertical = 3.dp)
                            .sharedBounds(
                                enter = fadeIn(tween(durationMillis = MyAnimationManager.ANIMATION_SPEED)),
                                exit = fadeOut(tween(durationMillis = MyAnimationManager.ANIMATION_SPEED)),
                                sharedContentState = rememberSharedContentState(key = "container_$route"),
                                animatedVisibilityScope = animatedContentScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            )
                    ) {
                        TransplantListItem(
                            headlineContent = { Text(route) },
                            leadingContent = {
                                Icon(
                                    painterResource(Res.drawable.deployed_code),
                                    null,
                                    modifier = Modifier.sharedElement(
                                        sharedContentState = rememberSharedContentState(key = "title_$route"),
                                        animatedVisibilityScope = animatedContentScope,
                                    )
                                ) },
                            modifier = Modifier.clickable {
                                onItemClick(route)
                            }
                        )
                    }
                }
            }
            // 有一些轻量级功能，只需要使用BottomSheet即可，无需打开新的页面
            items(6) { index ->
                SmallCard(modifier = Modifier.padding(horizontal = 3.dp, vertical = 3.dp)) {
                    PureScreen(index)
                }
            }
            items(2) {
                Spacer(Modifier.height(innerPadding.calculateBottomPadding()).navigationBarsPadding())
            }
        }
    }
}
