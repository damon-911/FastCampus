package fastcampus.part5.chapter3.feature.feed.data.mapper

import fastcampus.part5.chapter3.feature.common.entity.CategoryEntity
import fastcampus.part5.chapter3.feature.common.entity.MovieDetailEntity
import fastcampus.part5.chapter3.feature.common.entity.MovieFeedItemEntity
import fastcampus.part5.chapter3.feature.common.network.model.MovieResponse
import fastcampus.part5.chapter3.feature.feed.domain.SortOrder

fun MovieResponse.toMovieDetailEntity(): MovieDetailEntity = MovieDetailEntity(
    actors = this.actors,
    desc = this.desc,
    directors = this.directors,
    genre = this.genre,
    imageUrl = this.imageUrl,
    thumbUrl = this.thumbUrl,
    imdbPath = this.imdbPath,
    title = this.title,
    rating = this.rating,
    year = this.year
)

fun MovieDetailEntity.toMovieThumbnailEntity(): MovieFeedItemEntity = MovieFeedItemEntity(
    genre = this.genre,
    thumbUrl = this.thumbUrl,
    title = this.title,
    rating = this.rating,
    year = this.year
)

fun List<MovieFeedItemEntity>.toCategoryList(sortOrder: SortOrder): List<CategoryEntity> {
    val movieList = this
    val genreSet = mutableSetOf<String>().apply {
        addAll(movieList.flatMap { it.genre })
    }

    return mutableListOf<CategoryEntity>().also { feedItems ->
        genreSet.forEachIndexed { index, genreName ->
            this
                .filter { it.genre.contains(genreName) }
                .sortedByDescending {
                    when (sortOrder) {
                        SortOrder.RATING -> it.rating
                        SortOrder.YEAR -> it.year?.toFloat()
                    }
                }
                .let {
                    feedItems.add(
                        CategoryEntity(
                            id = index,
                            genre = genreName,
                            movieFeedEntities = it
                        )
                    )
                }
        }
    }
}