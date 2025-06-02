package com.xah.sample.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.xah.sample.logic.util.getKeyStackTrace
import com.xah.sample.ui.theme.AppTheme
import com.xah.sample.ui.screen.App

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
//        setCurrentActivity(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                App()
            }
        }
    }
}
