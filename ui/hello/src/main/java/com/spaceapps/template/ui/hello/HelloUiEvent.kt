package com.spaceapps.template.ui.hello

import androidx.annotation.StringRes

sealed class HelloUiEvent {
    data class OnButtonClick(val url: String) : HelloUiEvent()

    data class ShowToast(
        @StringRes val id: Int,
    ) : HelloUiEvent()
}
