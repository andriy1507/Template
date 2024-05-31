package com.spaceapps.template.app

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.spaceapps.template.core.navigation.NavigationEvent
import com.spaceapps.template.core.navigation.Navigator
import com.spaceapps.template.core.navigation.Routes
import com.spaceapps.template.core.ui.TemplateTheme
import com.spaceapps.template.core.ui.activity.MainActivityUiEvent
import com.spaceapps.template.core.ui.activity.MainActivityUiEventDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var startDestination: String? = null

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var eventDispatcher: MainActivityUiEventDispatcher

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
                val lifecycleOwner = LocalLifecycleOwner.current
                val navController = rememberNavController()
                val navigationEvents = rememberNavigationEvents(lifecycleOwner = lifecycleOwner)
                val uiEvents = rememberEvents(lifecycleOwner = lifecycleOwner)
                val snackBarHostState = remember { SnackbarHostState() }
                LaunchedEffect(Unit) {
                    navigationEvents.collect { event -> event(navController) }
                }
                LaunchedEffect(Unit) {
                    uiEvents.collect { event ->
                        when (event) {
                            is MainActivityUiEvent.ShowSnackBar ->
                                snackBarHostState.showSnackbar(getString(event.message, event.args))
                        }
                    }
                }
                TemplateTheme {
                    MainScreen(
                        navController = navController,
                        startDestination = provideStartDestination(),
                        snackBarHostState = snackBarHostState,
                    )
                }
            }
        }
        lifecycleScope.launch {
            withContext(Dispatchers.IO) { startDestination = provideStartDestination() }
        }
    }

    private fun provideStartDestination() = Routes.Auth.SignIn

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

    @Composable
    private fun rememberNavigationEvents(lifecycleOwner: LifecycleOwner): Flow<NavigationEvent> {
        return remember(navigator.emitter, lifecycleOwner) {
            navigator.emitter.flowWithLifecycle(
                lifecycleOwner.lifecycle,
                Lifecycle.State.STARTED,
            )
        }
    }

    @Composable
    private fun rememberEvents(lifecycleOwner: LifecycleOwner): Flow<MainActivityUiEvent> {
        return remember {
            eventDispatcher.emitter.flowWithLifecycle(
                lifecycleOwner.lifecycle,
                Lifecycle.State.STARTED,
            )
        }
    }
}
