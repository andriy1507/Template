package com.spaceapps.template.feature.auth.signIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    uiState: SignInUiState,
    uiEvents: Flow<SignInUiEvent>,
    onAction: OnAction,
) {
    LaunchedEffect(uiEvents) {
        uiEvents.collect { event ->
            // TODO:
        }
    }
    Column(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        TextField(
            value = uiState.username,
            onValueChange = { username ->
                onAction(SignInUiAction.UsernameEntered(username = username))
            },
        )
        TextField(
            value = uiState.password,
            onValueChange = { password ->
                onAction(SignInUiAction.PasswordEntered(password = password))
            },
        )
        Button(
            onClick = { onAction(SignInUiAction.SignInButtonClick) },
        ) {
            Text(text = "Auth")
        }
    }
}
