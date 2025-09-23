package com.xah.sample.ui.screen.home.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
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
import com.xah.transition.component.containerShare
import com.xah.transition.component.iconElementShare
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SingleColumnSampleScreen(
    navController : NavHostController,
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
        title = "单列样式",
        navController = navController,
    ) { innerPadding ->
        LazyColumn(state = scrollState) {
            item { Spacer(Modifier.height(innerPadding.calculateTopPadding()).statusBarsPadding()) }
            items(func.size, key = { func[it] }) { index ->
                val route = func[index]
                StyleCardListItem(
                    cardModifier = Modifier.containerShare(route=route) ,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    headlineContent = { Text(route) },
                    supportingContent = { Text("内容$index")},
                    leadingContent = {
                        Icon(
                            painterResource(Res.drawable.deployed_code),
                            null,
                            modifier = Modifier.iconElementShare(  route = route)
                        )
                    },
                    modifier = Modifier.clickable {
                        onItemClick(route)
                    }
                )
            }
            item { Spacer(Modifier.height(CARD_NORMAL_DP)) }
            items(func2.size, key = { func2[it] }) { index ->
                val route = func2[index]
                ListItem(
                    modifier = Modifier.containerShare(route=route)
                        .clickable {
                            onItemClick(route)
                        },
                    headlineContent = { Text(route) },
                    leadingContent = {
                        Icon(
                            painterResource(Res.drawable.deployed_code),
                            null,
                            modifier = Modifier.iconElementShare(  route = route)
                        )
                    },
                    supportingContent = {
                        Text("内容${index + 1}")
                    }
                )
            }
            item {
                Spacer(
                    Modifier.height(innerPadding.calculateBottomPadding() + APP_HORIZONTAL_DP).navigationBarsPadding()
                )
            }
        }
    }
}