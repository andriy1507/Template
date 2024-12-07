package com.spaceapps.template.feature.auth.signIn

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInUiState(
    val username: String,
    val password: String
) : Parcelable {
    companion object {
        val Empty = SignInUiState(username = "", password = "")
    }
}
