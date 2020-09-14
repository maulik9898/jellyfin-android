package org.jellyfin.mobile.ui.utils

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import org.jellyfin.mobile.R

@Composable
fun Context.ContextTheme(content: @Composable () -> Unit) {
    val colors = remember {
        darkColors(
            primary = Color(ContextCompat.getColor(this, R.color.jellyfin_primary)),
            primaryVariant = Color(ContextCompat.getColor(this, R.color.jellyfin_primary_dark)),
            secondary = Color(ContextCompat.getColor(this, R.color.jellyfin_accent)),
            background = Color(ContextCompat.getColor(this, R.color.theme_background)),
            surface = Color(ContextCompat.getColor(this, R.color.theme_surface)),
            error = Color(ContextCompat.getColor(this, R.color.error_text_color)),
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = Color.White,
            onSurface = Color.White,
            onError = Color.White,
        )
    }
    MaterialTheme(colors = colors, content = content)
}
