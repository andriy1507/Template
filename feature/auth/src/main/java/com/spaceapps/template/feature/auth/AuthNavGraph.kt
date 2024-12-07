package com.spaceapps.template.feature.auth

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.spaceapps.template.core.navigation.Routes
import com.spaceapps.template.feature.auth.signIn.SignInScreen
import com.spaceapps.template.feature.auth.signIn.SignInViewModel

fun NavGraphBuilder.authNavGraph() {
    composable(Routes.Auth.SignIn) {
        val viewModel = hiltViewModel<SignInViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        SignInScreen(
            uiState = uiState,
            uiEvents = viewModel.uiEvents,
            onAction = viewModel::onActionSubmitted
        )
    }
}
