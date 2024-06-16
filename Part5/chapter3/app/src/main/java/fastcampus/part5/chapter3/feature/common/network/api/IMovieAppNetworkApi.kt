package fastcampus.part5.chapter3.feature.common.network.api

import fastcampus.part5.chapter3.library.network.model.ApiResult
import fastcampus.part5.chapter3.feature.common.network.model.MovieResponse

interface IMovieAppNetworkApi {
    suspend fun getMovies(): ApiResult<List<MovieResponse>>
}