package fastcampus.part5.chapter3.feature.feed.domain.usecase

import fastcampus.part5.chapter3.feature.common.entity.CategoryEntity
import fastcampus.part5.chapter3.feature.common.entity.EntityWrapper
import fastcampus.part5.chapter3.feature.feed.domain.SortOrder

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
}
