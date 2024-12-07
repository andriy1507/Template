package com.spaceapps.template.feature.settings.appsettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spaceapps.template.core.ui.dp16
import kotlinx.coroutines.flow.Flow

@Composable
fun AppSettingsScreen(
    modifier: Modifier = Modifier,
    uiState: AppSettingsUiState,
    uiEvents: Flow<AppSettingsUiEvent>,
    onAction: OnAction
) {
    LaunchedEffect(uiEvents) {
        // TODO: Handle UI events
    }
    LazyColumn(
        modifier =
        modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        item {
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dp16),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Use Dynamic Theme")
                Switch(
                    checked = uiState.isDynamicColors,
                    onCheckedChange = { onAction(AppSettingsUiAction.SetIsDynamicColors(it)) }
                )
            }
        }

        items(3) {
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(end = dp16),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = false, onClick = {})
                Text("Use Dynamic Theme")
            }
        }
    }
}
