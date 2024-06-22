package fastcampus.part5.chapter4.ui_components.config

import androidx.compose.material3.Typography
import fastcampus.part5.chapter4.ui_components.theme.color.ColorSet

data class ComponentConfig(
    val colorScheme: ColorSet,
    val typography: Typography,
    val isDarkTheme: Boolean
)