package fastcampus.part5.chapter4.features.feed.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IFeedViewModelOutput {
    val feedState: StateFlow<FeedState>
    val feedUiEffect: SharedFlow<FeedUiEffect>
}

sealed class FeedUiEffect {
    data class OpenRestaurantDetail(val id: Int) : FeedUiEffect()
    object OpenInfoDialog : FeedUiEffect()
}