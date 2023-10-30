package fastcampus.part3.chapter5.service

import fastcampus.part3.chapter5.R
import fastcampus.part3.chapter5.model.ImageListResponse
import fastcampus.part3.chapter5.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {

    @Headers("Authorization: KakaoAK ${R.string.REST_API_KEY}")
    @GET("image")
    fun searchImage(@Query("query") query: String): Observable<ImageListResponse>

    @Headers("Authorization: KakaoAK ${R.string.REST_API_KEY}")
    @GET("vclip")
    fun searchVideo(@Query("query") query: String): Observable<VideoListResponse>
}