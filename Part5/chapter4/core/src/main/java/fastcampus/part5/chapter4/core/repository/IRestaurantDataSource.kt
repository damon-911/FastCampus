package fastcampus.part5.chapter4.core.repository

import fastcampus.part5.chapter4.core.entity.CategoryEntity
import fastcampus.part5.chapter4.core.entity.EntityWrapper
import fastcampus.part5.chapter4.core.entity.RestaurantDetailEntity

interface IRestaurantDataSource {
    suspend fun getCategories(): EntityWrapper<List<CategoryEntity>>
    suspend fun getRestaurantDetail(id: Int): RestaurantDetailEntity
}