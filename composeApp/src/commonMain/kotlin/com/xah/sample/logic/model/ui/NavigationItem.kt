package com.xah.sample.logic.model.ui

import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem (
    val route: String,
    val label: String,
    val icon: Painter,
    val filledIcon: Painter
)
