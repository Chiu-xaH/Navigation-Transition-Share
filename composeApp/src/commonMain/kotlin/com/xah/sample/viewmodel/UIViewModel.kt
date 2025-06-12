package com.xah.sample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.xah.sample.logic.util.SimpleDataStore
import com.xah.sample.ui.util.MyAnimationManager

class UIViewModel : ViewModel() {
    var showSurface by mutableStateOf(false)
    var isExpandedScreen by mutableStateOf(false)
    var firstStart by mutableStateOf(true)
    var animationSpeed by mutableFloatStateOf(MyAnimationManager.DEFAULT_ANIMATION_SPEED.toFloat())
    var isCenterAnimation by mutableStateOf(SimpleDataStore.IS_CENTER_ANIMATION)
    var motionBlur by mutableStateOf(SimpleDataStore.MOTION_BLUR)
    var forceAnimation by mutableStateOf(SimpleDataStore.FORCE_ANIMATION)
}
