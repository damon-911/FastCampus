package fastcampus.part5.chapter4.features.feed.domain.usecase

import fastcampus.part5.chapter4.core.entity.CategoryEntity
import fastcampus.part5.chapter4.core.entity.EntityWrapper
import fastcampus.part5.chapter4.core.repository.IRestaurantDataSource
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(
    private val dataSource: IRestaurantDataSource,
) : IGetFeedCategoryUseCase {
    override suspend fun invoke(): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories()
    }
}