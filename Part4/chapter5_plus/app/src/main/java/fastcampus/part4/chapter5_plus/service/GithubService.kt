package fastcampus.part4.chapter5_plus.service

import fastcampus.part4.chapter5_plus.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): List<Repo>
}