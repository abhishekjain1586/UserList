package com.anz.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBD0233),
    onPrimary = Color(0xFFFFFFFF),
    background = Color(0xFFEBEBEF),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1B1721),
    onSurfaceVariant = Color(0xFF54536A),
    inverseOnSurface = Color(0xFF72708F),
    secondary = Color(0xFF1DB954),
    tertiary = Color(0xFFFFF0F4),
    onTertiary = Color(0xFFBD0233)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFBD0233),
    onPrimary = Color(0xFFFFFFFF),
    background = Color(0xFFEBEBEF),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1B1721),
    onSurfaceVariant = Color(0xFF54536A),
    inverseOnSurface = Color(0xFF72708F),
    secondary = Color(0xFF1DB954),
    tertiary = Color(0xFFFFF0F4),
    onTertiary = Color(0xFFBD0233)
)

@Composable
fun UserListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*/

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}