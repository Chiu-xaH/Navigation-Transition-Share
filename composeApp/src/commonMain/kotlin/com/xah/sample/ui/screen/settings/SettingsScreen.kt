package com.xah.sample.ui.screen.settings

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import animationsample.composeapp.generated.resources.deployed_code
import animationsample.composeapp.generated.resources.settings
import com.xah.sample.logic.model.ui.ScreenRoute
import com.xah.sample.ui.component.APP_HORIZONTAL_DP
import com.xah.sample.ui.component.CustomSlider
import com.xah.sample.ui.component.DividerTextExpandedWith
import com.xah.sample.ui.component.MyCustomCard
import com.xah.sample.ui.component.StyleCardListItem
import com.xah.sample.ui.component.TransplantListItem
import com.xah.sample.ui.style.topBarTransplantColor
import com.xah.transition.component.TopBarNavigateIcon
import com.xah.transition.component.TransitionScaffold
import com.xah.transition.component.containerShare
import com.xah.transition.component.iconElementShare
import com.xah.transition.state.TransitionConfig
import com.xah.transition.style.TransitionLevel
import com.xah.transition.util.navigateAndSaveForTransition
import org.jetbrains.compose.resources.painterResource

private val transitionLevels = TransitionLevel.entries

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController : NavHostController,
) {
    val route = remember { ScreenRoute.SettingsScreen.route }

    var transition by remember { mutableStateOf(TransitionConfig.transitionBackgroundStyle.level) }
    LaunchedEffect(transition) {
         TransitionConfig.transitionBackgroundStyle.level = transition
    }

    TransitionScaffold(
        route = route,
        navHostController = navController,
        topBar = {
            TopAppBar(
                title = { Text("设置") },
                navigationIcon = {
                    TopBarNavigateIcon(navController,route, painterResource(Res.drawable.settings))
                },
                colors = topBarTransplantColor(),
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
        ){
            DividerTextExpandedWith("动效") {
                MyCustomCard(containerColor = MaterialTheme.colorScheme.surface) {
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
                        steps = transitionLevels.size-2,
                        modifier = Modifier.padding(bottom = APP_HORIZONTAL_DP),
                        valueRange = 0f..(transitionLevels.size-1).toFloat(),
                    )
                }
            }

            DividerTextExpandedWith("三级界面") {
                val r = ScreenRoute.Module31Screen.route
                StyleCardListItem(
                    color = MaterialTheme.colorScheme.surface,
                    cardModifier = Modifier.containerShare(route=r, roundShape = MaterialTheme.shapes.medium),
                    modifier = Modifier.clickable {
                        navController.navigateAndSaveForTransition(r)
                    },
                    headlineContent = { Text(r) },
                    leadingContent = {
                        Icon(
                            painterResource(Res.drawable.deployed_code),
                            null,
                            modifier = Modifier.iconElementShare( route = r)
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


