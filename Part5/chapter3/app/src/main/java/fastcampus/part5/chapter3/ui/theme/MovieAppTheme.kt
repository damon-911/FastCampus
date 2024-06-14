package fastcampus.part5.chapter3.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import fastcampus.part5.chapter3.ui.config.ComponentConfig
import fastcampus.part5.chapter3.ui.config.DefaultComponentConfig
import fastcampus.part5.chapter3.ui.theme.color.ColorSet
import fastcampus.part5.chapter3.ui.theme.color.MyColors

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.MyLightColorScheme }

@Composable
fun MovieAppTheme(
    themeState: State<ComponentConfig> = mutableStateOf(
        DefaultComponentConfig.RED_THEME
    ),
    content: @Composable () -> Unit
) {
    val myTheme by remember {
        themeState
    }

    val colorScheme = if (myTheme.isDarkTheme) {
        myTheme.colorScheme.MyDarkColorScheme
    } else {
        myTheme.colorScheme.MyLightColorScheme
    }

    CompositionLocalProvider(LocalColors provides colorScheme) {
        MaterialTheme(
            colorScheme = colorScheme.material,
            typography = myTheme.typography,
            content = content
        )
    }
}

val MaterialTheme.colorSchemes: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current