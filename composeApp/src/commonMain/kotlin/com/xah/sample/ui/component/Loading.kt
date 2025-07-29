package com.xah.sample.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xah.sample.ui.style.CenterScreen
import com.xah.sample.ui.style.RowHorizontal

@Composable
fun LoadingUI(
    text : String? = null,
) {
    Column {
        RowHorizontal {
            CircularProgressIndicator()
        }
        if(text != null) {
            Spacer(modifier = Modifier.height(2.dp))
            RowHorizontal {
                Text(text, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun LoadingScreen(
    text : String? = null,
) {
    CenterScreen {
        LoadingUI(text)
    }
}
