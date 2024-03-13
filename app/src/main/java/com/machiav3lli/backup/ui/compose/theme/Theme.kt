package com.machiav3lli.backup.ui.compose.theme

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.preferences.pref_appTheme
import com.machiav3lli.backup.utils.isBlackTheme
import com.machiav3lli.backup.utils.isDynamicTheme

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val blackTheme by remember { mutableStateOf(isBlackTheme) }

    MaterialTheme(
        colorScheme = when {
            OABX.minSDK(Build.VERSION_CODES.S) != null && isDynamicTheme -> {
                when {
                    isSystemInDarkTheme() && blackTheme && OABX.minSDK(Build.VERSION_CODES.S) >= Build.VERSION_CODES.S -> dynamicBlackColorScheme(context)
                    isSystemInDarkTheme() && OABX.minSDK(Build.VERSION_CODES.S) >= Build.VERSION_CODES.S -> dynamicDarkColorScheme(context)
                    OABX.minSDK(Build.VERSION_CODES.S) >= Build.VERSION_CODES.S -> dynamicLightColorScheme(context)
                    else -> LightColors
                }
            }

            darkTheme && blackTheme -> BlackColors
            darkTheme -> DarkColors
            else -> LightColors
        },
        content = content
    )
}

@RequiresApi(Build.VERSION_CODES.S)
fun dynamicColorScheme(context: Context, isDynamicDark: Boolean = isSystemInDarkTheme()): ColorScheme {
    return if (isDynamicDark) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }.copy(
        background = if (isBlackTheme) Color.Black else surfaceContainerLowest,
    )
}

// LIGHT THEMES
private val LightColors = lightColorScheme(
    // ...
)

// DARK THEMES
private val DarkColors = darkColorScheme(
    // ...
)

// Black Themes
private val BlackColors = DarkColors.copy(
    background = Color.Black,
    surfaceContainerLowest = Color.Black,
)
