package com.xah.sample.ui.screen.home.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.APP_HORIZONTAL_DP
import com.xah.sample.ui.component.SmallCard
import com.xah.sample.ui.component.StyleCardListItem
import com.xah.sample.ui.component.TransplantListItem
import com.xah.sample.ui.screen.detail.lite.LiteScreen
import com.xah.sample.ui.screen.home.screen.common.SharedTopBar
import com.xah.transition.component.containerShare
import com.xah.transition.component.iconElementShare
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun TwoColumnSampleScreen(
    navController : NavHostController,
    onItemClick: (String) -> Unit,
) {
    // 用于回退时保存滑动位置
    var scrollState = rememberLazyGridState()
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

    SharedTopBar(
        title = "双列样式",
        navController = navController,
    ) { innerPadding ->
        LazyVerticalGrid(
            state = scrollState,
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            items(2) {
                Spacer(Modifier.height(innerPadding.calculateTopPadding()).statusBarsPadding())
            }
            items(func.size, key = { func[it] }) { index ->
                val route = func[index]
                SmallCard(
                    modifier = Modifier.padding( 3.dp).containerShare(route)
                    ,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    TransplantListItem(
                        headlineContent = { Text(
                            route,
                        ) },
                        leadingContent = {
                            Icon(
                                painterResource(Res.drawable.deployed_code),
                                null,
                                modifier = Modifier.iconElementShare( route = route)
                            )
                        },
                        modifier = Modifier.clickable {
                            onItemClick(route)
                        }
                    )
                }
            }
            // 有一些轻量级功能，只需要使用BottomSheet即可，无需打开新的页面
            items(6) { index ->
                SmallCard(modifier = Modifier.padding(horizontal = 3.dp, vertical = 3.dp)) {
                    LiteScreen(index)
                }
            }
            items(2) {
                Spacer(
                    Modifier.height(innerPadding.calculateBottomPadding() + APP_HORIZONTAL_DP).navigationBarsPadding()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun RSampleScreen(
    navController : NavHostController,
    onItemClick: (String) -> Unit,
) {
    // 用于回退时保存滑动位置
    val route = ScreenRoute.ModuleScreen.route + "R" + 1
    SharedTopBar(
        title = "递归嵌套",
        navController = navController,
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            StyleCardListItem(
                headlineContent = { Text("进入递归") },
                leadingContent = {
                    Icon(
                        painterResource(Res.drawable.deployed_code),
                        null,
                        modifier = Modifier.iconElementShare( route = route)
                    )
                },
                cardModifier = Modifier.align(Alignment.Center).containerShare(route)
                ,
                modifier = Modifier.clickable {
                    onItemClick(route)
                },
                color = MaterialTheme.colorScheme.primaryContainer

            )
        }
    }
}
