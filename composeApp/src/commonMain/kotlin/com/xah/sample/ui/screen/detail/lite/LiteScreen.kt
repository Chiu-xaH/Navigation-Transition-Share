package com.xah.sample.ui.screen.detail.lite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import animationsample.composeapp.generated.resources.Res
import animationsample.composeapp.generated.resources.deployed_code
import com.xah.sample.ui.component.APP_HORIZONTAL_DP
import com.xah.sample.ui.component.BottomSheetTopBar
import com.xah.sample.ui.component.CustomBottomSheet
import com.xah.sample.ui.component.StyleCardListItem
import com.xah.sample.ui.component.TransplantListItem
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiteScreen(index : Int) {
    var showBottomSheet by remember { mutableStateOf(false) }

    TransplantListItem(
        headlineContent = { Text(text = "轻页面${index+1}") },
        leadingContent = {
            Icon(
                painterResource(Res.drawable.deployed_code),
                contentDescription = null,
            )
        },
        modifier = Modifier.clickable {
            showBottomSheet = true
        }
    )
    if (showBottomSheet) {
        CustomBottomSheet(
            isFullExpand = true,
            autoShape = false,
            onDismissRequest = { showBottomSheet = false },
        ) {
            Column {
                BottomSheetTopBar("作息")
                LazyColumn {
                    items(index+1) { i ->
                        StyleCardListItem(
                            headlineContent = { Text("轻页面")},
                            leadingContent = { Text("${i+1}")},
                            supportingContent = { Text("轻页面") }
                        )
                    }
                    item {
                        Spacer(Modifier.height(APP_HORIZONTAL_DP).navigationBarsPadding())
                    }
                }
            }
        }
    }
}
