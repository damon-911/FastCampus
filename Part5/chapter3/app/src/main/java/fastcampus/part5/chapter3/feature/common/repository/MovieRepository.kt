package fastcampus.part5.chapter3.feature.common.repository

import fastcampus.part5.chapter3.feature.common.entity.CategoryEntity
import fastcampus.part5.chapter3.feature.common.entity.EntityWrapper
import fastcampus.part5.chapter3.feature.common.entity.MovieDetailEntity
import fastcampus.part5.chapter3.feature.common.network.api.IMovieAppNetworkApi
import fastcampus.part5.chapter3.feature.feed.data.FeedConstants
import fastcampus.part5.chapter3.feature.feed.data.mapper.CategoryMapper
import fastcampus.part5.chapter3.feature.feed.domain.SortOrder
import fastcampus.part5.chapter3.library.storage.IStorage
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi,
    private val storage: IStorage,
    private val categoryMapper: CategoryMapper
) : IMovieDataSource {
    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkApi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()
    }
}