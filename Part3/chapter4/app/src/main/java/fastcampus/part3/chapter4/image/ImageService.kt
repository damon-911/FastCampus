package fastcampus.part3.chapter4.image

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-ID 5S56Cxn6QhWzo7huiXf27sQrLxnhneKr1Pmg1FfoFHc")
    @GET("random")
    fun getRandomImage(): Call<ImageResponse>

    @Headers("Authorization: Client-ID 5S56Cxn6QhWzo7huiXf27sQrLxnhneKr1Pmg1FfoFHc")
    @GET("random")
    fun getRandomImageRx(): Single<ImageResponse>

    @Headers("Authorization: Client-ID 5S56Cxn6QhWzo7huiXf27sQrLxnhneKr1Pmg1FfoFHc")
    @GET("random")
    suspend fun getRandomImageSuspend(): ImageResponse
}