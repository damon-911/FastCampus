package fastcampus.part2.chapter5.model

data class NewsModel(
    val title: String,
    val link: String,
    var imageUrl: String? = null
)

fun List<NewsItem>.transform() : List<NewsModel> {
    return this.map {
        NewsModel(
            title = it.title ?: "",
            link = it.link ?: "",
            imageUrl = null
        )
    }
}