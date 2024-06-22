package fastcampus.part5.chapter4.features.feed.data.mapper

import fastcampus.part5.chapter4.core.entity.CategoryEntity
import fastcampus.part5.chapter4.core.entity.EntityWrapper
import fastcampus.part5.chapter4.core.entity.RestaurantDetailEntity
import fastcampus.part5.chapter4.core.mapper.BaseMapper
import fastcampus.part5.chapter4.core.network.model.RestaurantResponse
import fastcampus.part5.chapter4.libraries.storage_contract.IStorage
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: IStorage,
) : BaseMapper<List<RestaurantResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<RestaurantResponse>?,
        extra: Any?,
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<RestaurantDetailEntity>()
                    .apply {
                        addAll(model.map { item -> item.toDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.RESTAURANT_LIST_KEY, it)
                    }
                    .toCategoryList()
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}