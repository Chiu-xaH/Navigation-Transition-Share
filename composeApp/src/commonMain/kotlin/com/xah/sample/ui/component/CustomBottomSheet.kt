package com.xah.sample.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xah.sample.ui.style.bottomSheetRound


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    onDismissRequest : () -> Unit,
    isFullExpand : Boolean = true,
    autoShape : Boolean = true,
    content : @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = isFullExpand)
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = null,
        shape = bottomSheetRound(sheetState,autoShape)
    ) {
        Column(){
            Spacer(Modifier.height(APP_HORIZONTAL_DP *1.5f))
            content()
        }
    }
}