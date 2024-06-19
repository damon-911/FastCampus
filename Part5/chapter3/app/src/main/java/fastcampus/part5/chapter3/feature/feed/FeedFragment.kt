package fastcampus.part5.chapter3.feature.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fastcampus.part5.chapter3.feature.feed.presentation.screen.FeedScreen
import fastcampus.part5.chapter3.feature.feed.presentation.viewmodel.FeedViewModel
import fastcampus.part5.chapter3.ui.theme.MovieAppTheme

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private val viewModel: FeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieAppTheme {
                    FeedScreen(
                        feedStateHolder = viewModel.output.feedState.collectAsState(),
                        input = viewModel.input
                    )
                }
            }
        }
    }
}