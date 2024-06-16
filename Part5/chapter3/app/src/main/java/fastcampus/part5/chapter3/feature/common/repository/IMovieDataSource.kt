package fastcampus.part5.chapter3.feature.common.repository

import fastcampus.part5.chapter3.feature.common.entity.CategoryEntity
import fastcampus.part5.chapter3.feature.common.entity.EntityWrapper
import fastcampus.part5.chapter3.feature.common.entity.MovieDetailEntity
import fastcampus.part5.chapter3.feature.feed.domain.SortOrder

interface IMovieDataSource {
    suspend fun getCategories(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}