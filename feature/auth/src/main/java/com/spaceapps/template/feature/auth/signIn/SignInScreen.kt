package com.spaceapps.template.feature.auth.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spaceapps.template.core.ui.dp16
import com.spaceapps.template.core.ui.dp8
import kotlinx.coroutines.flow.Flow

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    uiState: SignInUiState,
    uiEvents: Flow<SignInUiEvent>,
    onAction: OnAction
) {
    LaunchedEffect(uiEvents) {
        uiEvents.collect { event ->
            // TODO:
        }
    }
    Column(
        modifier =
        modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(dp8, alignment = Alignment.CenterVertically)
    ) {
        OutlinedTextField(
            modifier =
            Modifier
                .padding(horizontal = dp16)
                .fillMaxWidth(),
            value = uiState.username,
            onValueChange = { username ->
                onAction(SignInUiAction.UsernameEntered(username = username))
            },
            shape = CircleShape,
            singleLine = true
        )
        OutlinedTextField(
            modifier =
            Modifier
                .padding(horizontal = dp16)
                .fillMaxWidth(),
            value = uiState.password,
            onValueChange = { password ->
                onAction(SignInUiAction.PasswordEntered(password = password))
            },
            shape = CircleShape,
            singleLine = true
        )
        Button(
            modifier =
            Modifier
                .padding(horizontal = dp16)
                .fillMaxWidth(),
            onClick = { onAction(SignInUiAction.SignInButtonClick) }
        ) {
            Text(text = "Auth")
        }
    }
}
