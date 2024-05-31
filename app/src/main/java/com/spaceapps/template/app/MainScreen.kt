package com.spaceapps.template.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.spaceapps.template.R
import com.spaceapps.template.feature.auth.authNavGraph
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    snackBarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = WindowInsets.navigationBars) {
                repeat(3) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                painter =
                                    painterResource(
                                        id = R.drawable.ic_launcher_foreground,
                                    ),
                                contentDescription = null,
                            )
                        },
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    snackBarHostState.showSnackbar("Hello")
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            authNavGraph()
        }
    }
}
