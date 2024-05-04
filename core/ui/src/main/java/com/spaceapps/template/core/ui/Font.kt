package com.spaceapps.template.core.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

val DancingScript =
    FontFamily(
        Font(R.font.dancingscript_regular),
        Font(R.font.dancingscript_medium),
        Font(R.font.dancingscript_semibold),
        Font(R.font.dancingscript_bold),
    )

val titleTextStyle =
    TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = DancingScript,
        color = textColor,
        fontSize = sp48,
        textAlign = TextAlign.Center,
        lineHeight = sp64,
    )
val instructionTextStyle =
    TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = DancingScript,
        color = textColor,
        fontSize = sp36,
        textAlign = TextAlign.Center,
        lineHeight = sp48,
    )
val textFieldTextStyle =
    TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = DancingScript,
        color = textFieldColor,
        fontSize = sp24,
        textAlign = TextAlign.Center,
        lineHeight = sp36,
    )
val buttonTextStyle =
    TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = DancingScript,
        color = textColor,
        fontSize = sp24,
        textAlign = TextAlign.Center,
        lineHeight = sp36,
    )
