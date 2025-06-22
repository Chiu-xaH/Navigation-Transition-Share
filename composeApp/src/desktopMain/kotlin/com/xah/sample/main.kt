package com.xah.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.xah.sample.ui.screen.App


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "AnimationSample",
        alwaysOnTop = true
    ) {
        App()
    }
}