package fastcampus.part5.chapter3.feature.feed.domain.usecase

import fastcampus.part5.chapter3.feature.common.entity.CategoryEntity
import fastcampus.part5.chapter3.feature.common.entity.EntityWrapper
import fastcampus.part5.chapter3.feature.common.repository.IMovieDataSource
import fastcampus.part5.chapter3.feature.feed.domain.SortOrder
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(
    private val dataSource: IMovieDataSource
) : IGetFeedCategoryUseCase {
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories(sortOrder)
    }
}