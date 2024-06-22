package fastcampus.part5.chapter4.core.entity

import java.io.Serializable

data class RestaurantDetailEntity(
    override val id: Int = 0,
    override val neighborhood: String = "",
    override val name: String = "",
    override val photograph: String = "",
    override val rating: Float = 0f,
    val latlng: LatLngEntity = LatLngEntity(),
    val cuisineType: String = "",
    val address: String = "",
    val operatingHours: OperatingHoursEntity = OperatingHoursEntity(),
    val reviews: List<ReviewEntity> = listOf(),
) : RestaurantFeedItemEntity(
    id = id,
    neighborhood = neighborhood,
    name = name,
    photograph = photograph,
    rating = rating
), Serializable {
    data class LatLngEntity(
        val lat: Float = 0f,
        val lng: Float = 0f,
    ) : Serializable

    data class OperatingHoursEntity(
        val monday: String = "",
        val tuesday: String = "",
        val wednesday: String = "",
        val thursday: String = "",
        val friday: String = "",
        val saturday: String = "",
        val sunday: String = "",
    ) : Serializable

    data class ReviewEntity(
        val name: String = "",
        val date: String = "",
        val rating: Int = 0,
        val comments: String = "",
    ) : Serializable
}
