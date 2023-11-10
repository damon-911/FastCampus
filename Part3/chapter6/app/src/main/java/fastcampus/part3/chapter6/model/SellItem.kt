package fastcampus.part3.chapter6.model

import com.google.gson.annotations.SerializedName

data class SellItem(
    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("badge")
    val badge: String? = null,
    @SerializedName("rating")
    val rating: Float? = null,
) : ListItem {
    override val viewType: ViewType
        get() = ViewType.SELL_ITEM
}