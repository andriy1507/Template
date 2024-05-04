package com.spaceapps.template.app

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.spaceapps.template.core.ui.TemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var startDestination: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val transparent = Color.Transparent.toArgb()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(transparent, transparent),
            navigationBarStyle = SystemBarStyle.auto(transparent, transparent),
        )
        observeSplashScreenVisibility {
            setContent {
                TemplateTheme {

                }
            }
        }
        lifecycleScope.launch {
            withContext(Dispatchers.IO) { startDestination = provideStartDestination() }
        }
    }

    private fun provideStartDestination() = ""

    private fun observeSplashScreenVisibility(onReadyToDraw: () -> Unit) {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (startDestination != null) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        onReadyToDraw()
                        true
                    } else {
                        false
                    }
                }
            },
        )
    }
}
