package fastcampus.part5.chapter3.feature.feed.presentation.output

import fastcampus.part5.chapter3.feature.common.entity.CategoryEntity

sealed class FeedState {
    object Loading : FeedState()
    class Main(
        val categories: List<CategoryEntity>
    ) : FeedState()

    class Failed(
        val reason: String
    ) : FeedState()
}