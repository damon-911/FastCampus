package fastcampus.part5.chapter4.core.entity

data class CategoryEntity(
    val id: Int,
    val neighborhood: String,
    val feedEntities: List<RestaurantFeedItemEntity>,
)