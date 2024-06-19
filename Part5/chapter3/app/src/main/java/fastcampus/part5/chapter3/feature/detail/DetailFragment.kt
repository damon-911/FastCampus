package fastcampus.part5.chapter3.feature.detail

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
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import fastcampus.part5.chapter3.BaseFragment
import fastcampus.part5.chapter3.feature.detail.presentation.DetailScreen
import fastcampus.part5.chapter3.feature.detail.presentation.output.DetailUiEffect
import fastcampus.part5.chapter3.feature.detail.presentation.viewmodel.DetailViewModel
import fastcampus.part5.chapter3.ui.navigation.safeNavigate
import fastcampus.part5.chapter3.ui.theme.MovieAppTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        observeUiEffects()
        init()
        return ComposeView(requireContext()).apply {
            setContent {
                MovieAppTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    DetailScreen(
                        movieDetailState = viewModel.output.detailState.collectAsState(),
                        input = viewModel.input
                    )
                }
            }
        }
    }

    private fun init() {
        lifecycleScope.launch {
            viewModel.initMovieName(args.movieName)
        }
    }

    private fun observeUiEffects() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.output.detailUiEffect.collectLatest {
                    when (it) {
                        is DetailUiEffect.Back -> {
                            findNavController().navigateUp()
                        }

                        is DetailUiEffect.OpenUrl -> {
                            findNavController().safeNavigate(
                                DetailFragmentDirections.actionDetailToImdbDialog(
                                    it.url
                                )
                            )
                        }

                        is DetailUiEffect.RateMovie -> {
                            findNavController().safeNavigate(
                                DetailFragmentDirections.actionDetailToRating(
                                    movieName = it.movieTitle,
                                    rating = it.rating
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}