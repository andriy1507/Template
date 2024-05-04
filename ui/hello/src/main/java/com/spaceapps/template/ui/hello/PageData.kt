package com.spaceapps.template.ui.hello

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PageData(
    @DrawableRes val imageId: Int,
    @StringRes val textId: Int,
)
