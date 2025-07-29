package com.xah.sample

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.ComposeViewport
import animationsample.composeapp.generated.resources.NotoSans
import animationsample.composeapp.generated.resources.Res
import com.xah.sample.ui.component.LoadingUI
import com.xah.sample.ui.screen.App
import com.xah.sample.ui.style.CenterScreen
import kotlinx.browser.document
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.preloadFont

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val cjkFont by preloadFont(Res.font.NotoSans)
        val fontFamilyResolver = LocalFontFamilyResolver.current
        var fontLoaded by remember { mutableStateOf(false) }

        LaunchedEffect(cjkFont) {
            cjkFont?.let {
                fontFamilyResolver.preload(FontFamily(it))
                fontLoaded = true
            }
        }

        if (fontLoaded) {
            App()
        } else {
            CenterScreen {
                LoadingUI()
            }
        }
    }
}