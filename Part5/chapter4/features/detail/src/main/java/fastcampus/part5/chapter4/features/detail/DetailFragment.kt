package fastcampus.part5.chapter4.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import fastcampus.part5.chapter4.features.detail.presentation.screen.DetailScreen
import fastcampus.part5.chapter4.features.detail.presentation.output.DetailUiEffect
import fastcampus.part5.chapter4.features.detail.presentation.viewmodel.DetailViewModel
import fastcampus.part5.chapter4.ui_components.navigation.safeNavigate
import fastcampus.part5.chapter4.ui_components.theme.RestaurantAppTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : fastcampus.part5.chapter4.core.BaseFragment() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        observeUiEffects()
        init()
        return ComposeView(requireContext()).apply {
            setContent {
                RestaurantAppTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    DetailScreen(
                        restaurantDetailState = viewModel.output.detailState.collectAsState(),
                        input = viewModel.input
                    )
                }
            }
        }
    }

    private fun init() {
        val id = arguments?.getInt("id") ?: 0
        lifecycleScope.launch {
            viewModel.initDetail(id)
        }
    }

    private fun observeUiEffects() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.output.detailUiEffect.collectLatest {
                    when (it) {
                        is DetailUiEffect.Back -> {
                            findNavController().safeNavigate(
                                "App://Feed"
                            )
                        }

                        is DetailUiEffect.OpenUrl -> {
                            findNavController().safeNavigate(
                                "App://Map/${it.url}"
                            )
                        }

                        is DetailUiEffect.RateRestaurant -> {
                            findNavController().safeNavigate(
                                "App://Rating/${it.restaurantName}/${it.rating}"
                            )
                        }
                    }
                }
            }
        }
    }
}