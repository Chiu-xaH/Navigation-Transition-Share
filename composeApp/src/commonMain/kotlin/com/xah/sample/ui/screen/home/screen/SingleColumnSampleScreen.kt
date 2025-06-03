package com.xah.sample.ui.screen.home.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.APP_HORIZONTAL_DP
import com.xah.sample.ui.component.CARD_NORMAL_DP
import com.xah.sample.ui.component.StyleCardListItem
import com.xah.sample.ui.screen.home.screen.common.SharedTopBar
import com.xah.sample.ui.util.MyAnimationManager
import com.xah.sample.viewmodel.UIViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SingleColumnSampleScreen(
    vm: UIViewModel,
    navController : NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    boundsTransform: BoundsTransform,
    onItemClick: (String) -> Unit,
) {
    // 用于回退时保存滑动位置
    var scrollState = rememberLazyListState()
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

//            ScreenRoute.Module21Screen.route,
//            ScreenRoute.Module22Screen.route,
//            ScreenRoute.Module23Screen.route,
//            ScreenRoute.Module24Screen.route,
//            ScreenRoute.Module25Screen.route,
//            ScreenRoute.Module26Screen.route,
//            ScreenRoute.Module27Screen.route,
//            ScreenRoute.Module28Screen.route,
//            ScreenRoute.Module29Screen.route,
//            ScreenRoute.Module30Screen.route,
        )
    }
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

    SharedTopBar(
        vm,
        title = "单列样式",
        navController = navController,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        boundsTransform
    ) { innerPadding ->
        LazyColumn(state = scrollState) {
            item { Spacer(Modifier.height(innerPadding.calculateTopPadding()).statusBarsPadding()) }
            items(func.size, key = { func[it] }) { index ->
                val route = func[index]
                with(sharedTransitionScope) {
                    StyleCardListItem(
                        cardModifier = Modifier
                            .sharedBounds(
                                boundsTransform = boundsTransform,
                                enter = MyAnimationManager.fadeAnimation.enter,
                                exit = MyAnimationManager.fadeAnimation.exit,
                                sharedContentState = rememberSharedContentState(key = "container_$route"),
                                animatedVisibilityScope = animatedContentScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            ),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        headlineContent = { Text(route) },
                        supportingContent = { Text("内容$index")},
                        leadingContent = {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                modifier = Modifier.sharedElement(
                                    boundsTransform = boundsTransform,
                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                            )
                        },
                        modifier = Modifier.clickable {
                            onItemClick(route)
                        }
                    )
                }
            }
            item { Spacer(Modifier.height(CARD_NORMAL_DP)) }
            items(func2.size, key = { func2[it] }) { index ->
                val route = func2[index]
                with(sharedTransitionScope) {
                    ListItem(
                        modifier = Modifier
                            .sharedBounds(
                                boundsTransform = boundsTransform,
                                enter = MyAnimationManager.fadeAnimation.enter,
                                exit = MyAnimationManager.fadeAnimation.exit,
                                sharedContentState = rememberSharedContentState(key = "container_$route"),
                                animatedVisibilityScope = animatedContentScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            )
                            .clickable {
                                onItemClick(route)
                            },
//                        colors = MaterialTheme.colorScheme.secondaryContainer,
                        headlineContent = { Text(route) },
                        leadingContent = {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                modifier = Modifier.sharedElement(
                                    boundsTransform = boundsTransform,
                                    sharedContentState = rememberSharedContentState(key = "title_$route"),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                            )
                        },
                        supportingContent = {
                            Text("内容${index + 1}")
                        }
                    )
                }
            }
            item {
                Spacer(
                    Modifier.height(innerPadding.calculateBottomPadding() + APP_HORIZONTAL_DP).navigationBarsPadding()
                )
            }
        }
    }
}