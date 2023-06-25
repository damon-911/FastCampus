package fastcampus.part2.chapter4.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("language")
    val language: String?,

    @SerializedName("stargazers_count")
    val starCount: Int,

    @SerializedName("forks_count")
    val forkCount: Int,

    @SerializedName("html_url")
    val htmlUrl: String
)