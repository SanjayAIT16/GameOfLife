package com.jobik.gameoflife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jobik.gameoflife.screens.AppLayout.AppLayout
import com.jobik.gameoflife.ui.theme.AppThemeUtil
import com.jobik.gameoflife.ui.theme.GameOfLifeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        installSplashScreen()
        AppThemeUtil.restore(this)

        setContent {
            GameOfLifeTheme(darkTheme = AppThemeUtil.isDarkMode.value) {
                AppLayout()
            }
        }
    }
}
