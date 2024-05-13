package fastcampus.part4.chapter3.data

import androidx.annotation.DrawableRes

data class ItemData(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String,
)