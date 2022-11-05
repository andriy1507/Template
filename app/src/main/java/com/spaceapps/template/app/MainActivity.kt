package com.spaceapps.template.app

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.spaceapps.template.core.ui.TemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.spaceapps.template.ui.hello.HelloScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var startDestination: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setupEdgeToEdge()
        observeSplashScreenVisibility {
            setContent {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(Color.Transparent)
                TemplateTheme {
                    HelloScreen()
                }
            }
        }
        lifecycleScope.launch {
            withContext(Dispatchers.IO) { startDestination = provideStartDestination() }
        }
    }

    private fun provideStartDestination() = ""

    private fun setupEdgeToEdge() = WindowCompat.setDecorFitsSystemWindows(window, false)

    private fun observeSplashScreenVisibility(onReadyToDraw: () -> Unit) {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if (startDestination != null) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    onReadyToDraw()
                    true
                } else {
                    false
                }
            }
        })
    }
}
