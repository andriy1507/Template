package com.spaceapps.template.feature.settings.appsettings

data class AppSettingsUiState(
    val isDynamicColors: Boolean,
    val appTheme: AppTheme
) {
    enum class AppTheme {
        Light,
        Dark,
        System
    }
}
