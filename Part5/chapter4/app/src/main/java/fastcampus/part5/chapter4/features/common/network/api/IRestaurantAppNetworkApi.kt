package fastcampus.part5.chapter4.features.common.network.api

import fastcampus.part5.chapter4.libraries.network.model.ApiResult
import fastcampus.part5.chapter4.features.common.network.model.RestaurantResponse

interface IRestaurantAppNetworkApi {
    suspend fun getRestaurantsList(): ApiResult<List<RestaurantResponse>>
}