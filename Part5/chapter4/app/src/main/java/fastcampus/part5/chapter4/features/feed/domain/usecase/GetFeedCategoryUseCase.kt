package fastcampus.part5.chapter4.features.feed.domain.usecase

import fastcampus.part5.chapter4.features.common.entity.CategoryEntity
import fastcampus.part5.chapter4.features.common.entity.EntityWrapper
import fastcampus.part5.chapter4.features.common.repository.IRestaurantDataSource
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(
    private val dataSource: IRestaurantDataSource,
) : IGetFeedCategoryUseCase {
    override suspend fun invoke(): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories()
    }
}