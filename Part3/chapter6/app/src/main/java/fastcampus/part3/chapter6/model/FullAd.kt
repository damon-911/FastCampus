package fastcampus.part3.chapter6.model

import com.google.gson.annotations.SerializedName

data class FullAd(
    @SerializedName("title")
    val title: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("button")
    val button: String? = null,

    ) : ListItem {
    override val viewType: ViewType
        get() = ViewType.FULL_AD
}