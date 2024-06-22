package fastcampus.part5.chapter4.features.feed.presentation.input

interface IFeedViewModelInput {
    fun openDetail(id: Int)
    fun openInfoDialog()
    fun refreshFeed()
}