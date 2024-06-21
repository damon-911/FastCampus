package fastcampus.part5.chapter4.features.feed.domain.usecase

import fastcampus.part5.chapter4.core.entity.CategoryEntity
import fastcampus.part5.chapter4.core.entity.EntityWrapper

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(): EntityWrapper<List<CategoryEntity>>
}
