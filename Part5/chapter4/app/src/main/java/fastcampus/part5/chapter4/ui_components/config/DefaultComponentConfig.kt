package fastcampus.part5.chapter4.ui_components.config

import fastcampus.part5.chapter4.ui_components.theme.myTypography
import fastcampus.part5.chapter4.ui_components.theme.color.ColorSet

object DefaultComponentConfig {
    val RED_THEME = ComponentConfig(
        colorScheme = ColorSet.Red,
        typography = myTypography,
        isDarkTheme = false
    )

    val BLUE_THEME = ComponentConfig(
        colorScheme = ColorSet.Blue,
        typography = myTypography,
        isDarkTheme = false
    )
}