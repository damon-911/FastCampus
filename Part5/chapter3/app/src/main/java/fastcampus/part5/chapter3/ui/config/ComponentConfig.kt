package fastcampus.part5.chapter3.ui.config

import androidx.compose.material3.Typography
import fastcampus.part5.chapter3.ui.theme.color.ColorSet

data class ComponentConfig(
    val colorScheme: ColorSet,
    val typography: Typography,
    val isDarkTheme: Boolean
)