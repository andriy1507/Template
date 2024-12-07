package com.spaceapps.template.feature.auth.signIn

typealias OnAction = (SignInUiAction) -> Unit

sealed class SignInUiAction {
    data class UsernameEntered(val username: String) : SignInUiAction()

    data class PasswordEntered(val password: String) : SignInUiAction()

    data object SignInButtonClick : SignInUiAction()
}
