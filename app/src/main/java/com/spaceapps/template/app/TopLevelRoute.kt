package com.spaceapps.template.app

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TopLevelRoute(
    val route: String,
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int
)
