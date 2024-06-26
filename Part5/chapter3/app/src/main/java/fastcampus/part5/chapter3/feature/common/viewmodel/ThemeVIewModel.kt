package fastcampus.part5.chapter3.feature.common.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.chapter3.ui.config.ComponentConfig
import fastcampus.part5.chapter3.ui.config.DefaultComponentConfig
import fastcampus.part5.chapter3.ui.theme.color.ColorSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor() : ViewModel() {

    private val _themeState: MutableStateFlow<ComponentConfig> =
        MutableStateFlow(DefaultComponentConfig.RED_THEME)
    val themeState: StateFlow<ComponentConfig>
        get() = _themeState

    private val _nextColorState: MutableStateFlow<Color> =
        MutableStateFlow(DefaultComponentConfig.BLUE_THEME.colorScheme.MyLightColorScheme.primary)
    val nextColorState: StateFlow<Color>
        get() = _nextColorState

    fun toggleColorSet() {
        if (themeState.value.colorScheme is ColorSet.Red) {
            _themeState.value = DefaultComponentConfig.BLUE_THEME
            _nextColorState.value = ColorSet.Red.MyLightColorScheme.primary
        } else {
            _themeState.value = DefaultComponentConfig.RED_THEME
            _nextColorState.value = ColorSet.Blue.MyLightColorScheme.primary
        }
    }
}