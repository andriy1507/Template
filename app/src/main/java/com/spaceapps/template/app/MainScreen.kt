package com.spaceapps.template.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.spaceapps.template.feature.auth.authNavGraph
import com.spaceapps.template.feature.settings.settingsNavGraph

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    snackBarHostState: SnackbarHostState,
    topLevelRoutes: List<TopLevelRoute>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar(windowInsets = WindowInsets.navigationBars) {
                topLevelRoutes.forEach { topLevelRoute ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = topLevelRoute.iconRes),
                                contentDescription = null
                            )
                        },
                        label = { Text(text = stringResource(id = topLevelRoute.labelRes)) },
                        selected =
                        currentDestination?.hierarchy?.any {
                            it.route == topLevelRoute.route
                        } == true,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            authNavGraph()
            settingsNavGraph()
        }
    }
}
