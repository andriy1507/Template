package com.spaceapps.template.core.ui.activity

import androidx.annotation.StringRes

sealed interface MainActivityUiEvent {
    class ShowSnackBar(
        @StringRes val message: Int,
        vararg val args: Any,
    ) : MainActivityUiEvent
}
