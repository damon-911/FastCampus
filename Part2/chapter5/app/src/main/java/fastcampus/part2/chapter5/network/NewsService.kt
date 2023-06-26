package fastcampus.part2.chapter5.network

import fastcampus.part2.chapter5.model.NewsRss
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("rss?hl=ko&gl=KR&ceid=KR:ko")
    fun mainFeed(): Call<NewsRss>

    @GET("rss/topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRFZ4ZERBU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako")
    fun politicsNews(): Call<NewsRss>

    @GET("rss/topics/CAAqIggKIhxDQkFTRHdvSkwyMHZNR2RtY0hNekVnSnJieWdBUAE?hl=ko&gl=KR&ceid=KR%3Ako")
    fun economyNews(): Call<NewsRss>

    @GET("rss/topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRGs0ZDNJU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako")
    fun societyNews(): Call<NewsRss>

    @GET("rss/topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRE41ZEdNU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako")
    fun itNews(): Call<NewsRss>

    @GET("rss/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRFp1ZEdvU0FtdHZHZ0pMVWlnQVAB?hl=ko&gl=KR&ceid=KR%3Ako")
    fun sportNews(): Call<NewsRss>

    @GET("rss/search?hl=ko&gl=KR&ceid=KR%3Ako")
    fun search(@Query("q") query: String): Call<NewsRss>
}