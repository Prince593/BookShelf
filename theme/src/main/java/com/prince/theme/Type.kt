package com.prince.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val customFontFamily = FontFamily(
    Font(R.font.archivo, FontWeight.Normal),
    Font(R.font.archivo_medium, FontWeight.Medium),
    Font(R.font.archivo_bold, FontWeight.Bold),
)

val CustomFontTextStyle = TextStyle(fontFamily = customFontFamily)
