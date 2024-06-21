package fastcampus.part5.chapter4.features.common.repository

import fastcampus.part5.chapter4.features.common.entity.CategoryEntity
import fastcampus.part5.chapter4.features.common.entity.EntityWrapper
import fastcampus.part5.chapter4.features.common.entity.RestaurantDetailEntity
import fastcampus.part5.chapter4.features.common.network.api.IRestaurantAppNetworkApi
import fastcampus.part5.chapter4.features.feed.data.FeedConstants
import fastcampus.part5.chapter4.features.feed.data.mapper.CategoryMapper
import fastcampus.part5.chapter4.libraries.storage.IStorage
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val restaurantNetworkApi: IRestaurantAppNetworkApi,
    private val storage: IStorage,
    private val categoryMapper: CategoryMapper,
) : IRestaurantDataSource {
    override suspend fun getCategories(): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = restaurantNetworkApi.getRestaurantsList()
        )
    }

    override suspend fun getRestaurantDetail(id: Int): RestaurantDetailEntity {
        return storage
            .get<List<RestaurantDetailEntity>>(FeedConstants.RESTAURANT_LIST_KEY)
            ?.single { it.id == id }
            ?: RestaurantDetailEntity()
    }
}