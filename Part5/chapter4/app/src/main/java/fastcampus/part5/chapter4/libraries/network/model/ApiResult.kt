package fastcampus.part5.chapter4.libraries.network.model

import com.google.gson.annotations.SerializedName

class ApiResult<T>(
    @SerializedName("response")
    val response: ApiResponse<T>,
    @SerializedName("headers")
    val headers: Map<String, String> = emptyMap(),
    @SerializedName("code")
    val code: Int = -1,
)