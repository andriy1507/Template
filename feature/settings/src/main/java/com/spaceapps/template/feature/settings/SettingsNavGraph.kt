package com.spaceapps.template.feature.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.spaceapps.template.core.navigation.Routes
import com.spaceapps.template.feature.settings.appsettings.AppSettingsScreen
import com.spaceapps.template.feature.settings.appsettings.AppSettingsViewModel
import kotlinx.coroutines.flow.emptyFlow

fun NavGraphBuilder.settingsNavGraph() {
    composable(Routes.Settings.AppSettings) {
        val viewModel = hiltViewModel<AppSettingsViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        AppSettingsScreen(
            uiState = uiState,
            uiEvents = emptyFlow(),
            onAction = viewModel::onActionSubmitted
        )
    }
}
