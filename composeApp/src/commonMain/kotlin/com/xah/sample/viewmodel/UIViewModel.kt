package com.xah.sample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.xah.sample.logic.util.SimpleDataStore

class UIViewModel : ViewModel() {
    var isExpandedScreen by mutableStateOf(false)
    var firstStart by mutableStateOf(true)
    var hazeBlur by mutableStateOf(SimpleDataStore.HAZE_BLUR)
    var motionBlur by mutableStateOf(SimpleDataStore.MOTION_BLUR)
    var forceAnimation by mutableStateOf(SimpleDataStore.FORCE_ANIMATION)
    var transplantBackground by mutableStateOf(SimpleDataStore.TRANSPLANT_BACKGROUND)
}