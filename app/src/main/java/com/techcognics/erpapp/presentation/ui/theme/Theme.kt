package com.techcognics.erpapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkSecondaryColor,
    onPrimary = DarkOnPrimaryColor,
    primaryContainer = DarkPrimaryVariant,
    secondary = DarkSecondaryColor,
    onSecondary = DarkOnSecondaryColor,
    secondaryContainer = DarkSecondaryVariant,
    background = DarkBackgroundColor,
    onBackground = DarkOnBackgroundColor,
    surface = DarkSurfaceColor,
    onSurface = DarkOnSurfaceColor,
    error = DarkErrorColor,
    onError = DarkOnErrorColor,
)

private val LightColorScheme = lightColorScheme(

    primary = PrimaryColor,//main theme color.
    onPrimary = OnPrimaryColor, //This onPrimary Color is use for Icon and Text which come on the Primary color
    primaryContainer = OnPrimaryColor,
    onPrimaryContainer = OnSurfaceColor,
    secondary = SecondaryColor,
    onSecondary = OnSecondaryColor,
    secondaryContainer = OnSecondaryColor,
    onSecondaryContainer = SuccessColor,
    background = BackgroundColor,
    onBackground = OnBackgroundColor,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    error = ErrorColor,
    onError = OnErrorColor,
    tertiary = TertiaryColor,
    onTertiary = OnPrimaryColor,
    outline = PrimaryOutline,


    )

@Composable
fun ERPAPPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}