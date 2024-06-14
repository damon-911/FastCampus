package fastcampus.part5.chapter3.ui.config

import fastcampus.part5.chapter3.ui.theme.myTypography
import fastcampus.part5.chapter3.ui.theme.color.ColorSet

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

    private var currentConfig = RED_THEME
    private var themeColorSet: ColorSet = ColorSet.Red
}