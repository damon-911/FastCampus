package fastcampus.part5.chapter4.libraries.network_contract.api

import fastcampus.part5.chapter4.libraries.network_contract.model.ApiResult
import fastcampus.part5.chapter4.libraries.network_contract.model.NetworkRequestInfo
import java.lang.reflect.Type

interface NetworkRequestFactory {

    suspend fun <T> create(
        url: String,
        requestInfo: NetworkRequestInfo = NetworkRequestInfo.Builder().build(),
        type: Type
    ): ApiResult<T>
}