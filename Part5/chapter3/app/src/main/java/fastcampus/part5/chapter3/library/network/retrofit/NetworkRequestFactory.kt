package fastcampus.part5.chapter3.library.network.retrofit

import fastcampus.part5.chapter3.library.network.model.ApiResult
import fastcampus.part5.chapter3.library.network.model.NetworkRequestInfo
import java.lang.reflect.Type

interface NetworkRequestFactory {

    suspend fun <T> create(
        url: String,
        requestInfo: NetworkRequestInfo = NetworkRequestInfo.Builder().build(),
        type: Type
    ): ApiResult<T>
}