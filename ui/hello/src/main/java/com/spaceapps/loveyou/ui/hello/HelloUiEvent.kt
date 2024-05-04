package com.spaceapps.loveyou.ui.hello

import androidx.annotation.StringRes

sealed class HelloUiEvent {
    data class OnButtonClick(val url: String) : HelloUiEvent()

    data class ShowToast(
        @StringRes val id: Int,
    ) : HelloUiEvent()
}
