package com.spaceapps.loveyou.ui.hello

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PageData(
    @DrawableRes val imageId: Int,
    @StringRes val textId: Int,
)
