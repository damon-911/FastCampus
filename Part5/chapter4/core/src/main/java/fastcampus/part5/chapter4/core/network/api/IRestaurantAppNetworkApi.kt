package fastcampus.part5.chapter4.core.network.api

import fastcampus.part5.chapter4.core.network.model.RestaurantResponse
import fastcampus.part5.chapter4.libraries.network_contract.model.ApiResult

interface IRestaurantAppNetworkApi {
    suspend fun getRestaurantsList(): ApiResult<List<RestaurantResponse>>
}