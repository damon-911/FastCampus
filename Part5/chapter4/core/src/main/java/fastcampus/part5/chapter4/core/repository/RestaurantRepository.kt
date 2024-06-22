package fastcampus.part5.chapter4.core.repository

import fastcampus.part5.chapter4.core.entity.CategoryEntity
import fastcampus.part5.chapter4.core.entity.EntityWrapper
import fastcampus.part5.chapter4.core.entity.RestaurantDetailEntity
import fastcampus.part5.chapter4.core.mapper.CategoryMapper
import fastcampus.part5.chapter4.core.network.api.IRestaurantAppNetworkApi
import fastcampus.part5.chapter4.libraries.storage_contract.IStorage
import fastcampus.part5.chapter4.libraries.storage_contract.constants.StorageKey
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
            .get<List<RestaurantDetailEntity>>(StorageKey.RESTAURANT_LIST_KEY)
            ?.single { it.id == id }
            ?: RestaurantDetailEntity()
    }
}