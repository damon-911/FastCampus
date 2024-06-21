package fastcampus.part5.chapter4.features.feed.domain.usecase

import fastcampus.part5.chapter4.features.common.entity.CategoryEntity
import fastcampus.part5.chapter4.features.common.entity.EntityWrapper

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(): EntityWrapper<List<CategoryEntity>>
}
