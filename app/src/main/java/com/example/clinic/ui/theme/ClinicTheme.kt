package com.example.clinic.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme =
        lightColorScheme(
                primary = Purple500,
                onPrimary = White,
                secondary = Purple700,
                onSecondary = White,
                background = LightGray,
                onBackground = Black,
                surface = White,
                onSurface = Black
        )

private val DarkColorScheme =
        darkColorScheme(
                primary = Purple200,
                onPrimary = Black,
                secondary = Purple700,
                onSecondary = Black,
                background = DarkGray,
                onBackground = White,
                surface = Black,
                onSurface = White
        )

@Composable
fun ClinicTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(colorScheme = colors, typography = Typography, shapes = Shapes, content = content)
}
