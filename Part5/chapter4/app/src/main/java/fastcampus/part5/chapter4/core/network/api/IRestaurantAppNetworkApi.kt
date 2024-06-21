package fastcampus.part5.chapter4.core.network.api

import fastcampus.part5.chapter4.libraries.network.model.ApiResult
import fastcampus.part5.chapter4.core.network.model.RestaurantResponse

interface IRestaurantAppNetworkApi {
    suspend fun getRestaurantsList(): ApiResult<List<RestaurantResponse>>
}