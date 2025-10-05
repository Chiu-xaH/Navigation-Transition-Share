package com.xah.transition.style

import android.content.Context
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@RequiresApi(Build.VERSION_CODES.S)
@Composable
actual fun Modifier.blurEffect(radius: Dp): Modifier {
    val blurPx = with(LocalDensity.current) { radius.toPx() }

    val r = RenderEffect.createBlurEffect(
        blurPx, blurPx,
        Shader.TileMode.CLAMP
    ) .asComposeRenderEffect()

    return this.graphicsLayer {
        renderEffect = if (blurPx > 0f) {
            r
        } else null
    }
}
