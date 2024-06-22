package fastcampus.part5.chapter4.core.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.chapter4.libraries.storage_contract.IStorage
import fastcampus.part5.chapter4.libraries.storage_contract.constants.StorageKey
import fastcampus.part5.chapter4.ui_components.config.ComponentConfig
import fastcampus.part5.chapter4.ui_components.config.DefaultComponentConfig
import fastcampus.part5.chapter4.ui_components.theme.color.ColorSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    storage: IStorage,
) : ViewModel() {

    private val isDefaultThemeRed = storage.get<String>(StorageKey.APP_THEME) == "red"

    private val _themeState: MutableStateFlow<ComponentConfig> =
        MutableStateFlow(
            if (isDefaultThemeRed) {
                DefaultComponentConfig.RED_THEME
            } else {
                DefaultComponentConfig.BLUE_THEME
            }
        )
    val themeState: StateFlow<ComponentConfig>
        get() = _themeState

    private val _nextColorState: MutableStateFlow<Color> =
        MutableStateFlow(
            if (isDefaultThemeRed) {
                DefaultComponentConfig.BLUE_THEME.colorScheme.MyLightColorScheme.primary
            } else {
                DefaultComponentConfig.RED_THEME.colorScheme.MyLightColorScheme.primary
            }
        )
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