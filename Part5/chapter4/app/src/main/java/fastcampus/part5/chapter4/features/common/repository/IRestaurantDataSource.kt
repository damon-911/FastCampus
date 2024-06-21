package fastcampus.part5.chapter4.features.common.repository

import fastcampus.part5.chapter4.features.common.entity.CategoryEntity
import fastcampus.part5.chapter4.features.common.entity.EntityWrapper
import fastcampus.part5.chapter4.features.common.entity.RestaurantDetailEntity

interface IRestaurantDataSource {
    suspend fun getCategories(): EntityWrapper<List<CategoryEntity>>
    suspend fun getRestaurantDetail(id: Int): RestaurantDetailEntity
}