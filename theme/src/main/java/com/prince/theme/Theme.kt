package com.prince.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF353535),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun BookShelfTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MaterialTheme.typography.copy(
            displayLarge = MaterialTheme.typography.displayLarge.merge(CustomFontTextStyle),
            displayMedium = MaterialTheme.typography.displayMedium.merge(CustomFontTextStyle),
            displaySmall = MaterialTheme.typography.displaySmall.merge(CustomFontTextStyle),
            headlineLarge = MaterialTheme.typography.headlineLarge.merge(CustomFontTextStyle),
            headlineMedium = MaterialTheme.typography.headlineMedium.merge(CustomFontTextStyle),
            headlineSmall = MaterialTheme.typography.headlineSmall.merge(CustomFontTextStyle),
            titleLarge = MaterialTheme.typography.titleLarge.merge(CustomFontTextStyle),
            titleMedium = MaterialTheme.typography.titleMedium.merge(CustomFontTextStyle),
            titleSmall = MaterialTheme.typography.titleSmall.merge(CustomFontTextStyle),
            bodyLarge = MaterialTheme.typography.bodyLarge.merge(CustomFontTextStyle),
            bodyMedium = MaterialTheme.typography.bodyMedium.merge(CustomFontTextStyle),
            bodySmall = MaterialTheme.typography.bodySmall.merge(CustomFontTextStyle),
            labelLarge = MaterialTheme.typography.labelLarge.merge(CustomFontTextStyle),
            labelMedium = MaterialTheme.typography.labelMedium.merge(CustomFontTextStyle),
            labelSmall = MaterialTheme.typography.labelSmall.merge(CustomFontTextStyle),
        ),
        content = content
    )
}