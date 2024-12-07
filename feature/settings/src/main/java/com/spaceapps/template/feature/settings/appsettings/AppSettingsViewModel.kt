package com.spaceapps.template.feature.settings.appsettings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapps.template.core.navigation.Navigator
import com.spaceapps.template.core.ui.activity.MainActivityUiEventDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val mainActivityEventDispatcher: MainActivityUiEventDispatcher
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(AppSettingsUiState(false, AppSettingsUiState.AppTheme.System))
    val uiState: StateFlow<AppSettingsUiState>
        get() = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<AppSettingsUiEvent>()
    val uiEvents: SharedFlow<AppSettingsUiEvent>
        get() = _uiEvents.asSharedFlow()

    private val pendingActions = MutableSharedFlow<AppSettingsUiAction>()

    init {
        collectActions()
    }

    private fun collectActions() =
        viewModelScope.launch {
            pendingActions.collect { action ->
//            TODO()
            }
        }

    fun onActionSubmitted(action: AppSettingsUiAction) =
        viewModelScope.launch {
            pendingActions.emit(action)
        }
}
