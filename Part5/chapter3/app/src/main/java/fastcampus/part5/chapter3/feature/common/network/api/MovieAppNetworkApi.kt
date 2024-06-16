package fastcampus.part5.chapter3.feature.common.network.api

import com.google.gson.reflect.TypeToken
import fastcampus.part5.chapter3.feature.common.network.model.MovieResponse
import fastcampus.part5.chapter3.library.network.model.ApiResult
import fastcampus.part5.chapter3.library.network.retrofit.NetworkRequestFactory
import javax.inject.Inject

class MovieAppNetworkApi @Inject constructor(
    private val networkRequestFactory: NetworkRequestFactory
) : IMovieAppNetworkApi {
    override suspend fun getMovies(): ApiResult<List<MovieResponse>> {
        return networkRequestFactory.create(
            url = "top250.json",
            type = object : TypeToken<List<MovieResponse>>() {}.type
        )
    }
}