package fastcampus.part5.chapter4.core.network.api

import com.google.gson.reflect.TypeToken
import fastcampus.part5.chapter4.core.network.model.RestaurantResponse
import fastcampus.part5.chapter4.libraries.network_contract.api.NetworkRequestFactory
import fastcampus.part5.chapter4.libraries.network_contract.model.ApiResult
import javax.inject.Inject

class RestaurantAppNetworkApi @Inject constructor(
    private val networkRequestFactory: NetworkRequestFactory,
) : IRestaurantAppNetworkApi {
    override suspend fun getRestaurantsList(): ApiResult<List<RestaurantResponse>> {
        return networkRequestFactory.create(
            url = "restaurants.json",
            type = object : TypeToken<List<RestaurantResponse>>() {}.type
        )
    }
}