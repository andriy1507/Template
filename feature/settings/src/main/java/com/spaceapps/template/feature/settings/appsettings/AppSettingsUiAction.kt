package com.spaceapps.template.feature.settings.appsettings

typealias OnAction = (AppSettingsUiAction) -> Unit

sealed class AppSettingsUiAction {
    data class SetIsDynamicColors(val isDynamicColors: Boolean) : AppSettingsUiAction()

    data class SetAppTheme(val appTheme: AppSettingsUiState.AppTheme) : AppSettingsUiAction()
}
