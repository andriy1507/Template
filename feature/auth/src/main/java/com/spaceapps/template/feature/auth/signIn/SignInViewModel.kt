package com.spaceapps.template.feature.auth.signIn

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapps.template.core.navigation.Navigator
import com.spaceapps.template.core.ui.activity.MainActivityUiEvent
import com.spaceapps.template.core.ui.activity.MainActivityUiEventDispatcher
import com.spaceapps.template.feature.auth.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
        private val navigator: Navigator,
        private val mainActivityEventDispatcher: MainActivityUiEventDispatcher,
    ) : ViewModel() {
        private val username = savedStateHandle.getStateFlow("username", "")
        private val password = savedStateHandle.getStateFlow("password", "")
        private val pendingActions = MutableSharedFlow<SignInUiAction>()
        private val _uiEvents = MutableSharedFlow<SignInUiEvent>()

        val uiState =
            combine(username, password) { username, password ->
                SignInUiState(username = username, password = password)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5),
                initialValue = SignInUiState.Empty,
            )
        val uiEvents: SharedFlow<SignInUiEvent>
            get() = _uiEvents.asSharedFlow()

        init {
            collectActions()
        }

        private fun collectActions() =
            viewModelScope.launch {
                pendingActions.collect { action ->
                    when (action) {
                        is SignInUiAction.UsernameEntered -> onUsernameEntered(action.username)
                        is SignInUiAction.PasswordEntered -> onPasswordEntered(action.password)
                        is SignInUiAction.SignInButtonClick -> onAuthButtonClick()
                    }
                }
            }

        private fun onUsernameEntered(username: String) = savedStateHandle.set("username", username)

        private fun onPasswordEntered(password: String) = savedStateHandle.set("password", password)

        private fun onAuthButtonClick() =
            viewModelScope.launch {
                mainActivityEventDispatcher.emit(
                    MainActivityUiEvent.ShowSnackBar(
                        R.string.test,
                        "Uno",
                        "Dos",
                    ),
                )
            }

        fun onActionSubmitted(action: SignInUiAction) =
            viewModelScope.launch {
                pendingActions.emit(action)
            }
    }
