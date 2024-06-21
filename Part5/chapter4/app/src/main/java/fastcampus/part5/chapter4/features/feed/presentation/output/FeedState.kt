package fastcampus.part5.chapter4.features.feed.presentation.output

import fastcampus.part5.chapter4.features.common.entity.CategoryEntity

sealed class FeedState {
    object Loading : FeedState()

    class Main(
        val categories: List<CategoryEntity>,
    ) : FeedState()

    class Failed(
        val reason: String,
    ) : FeedState()
}