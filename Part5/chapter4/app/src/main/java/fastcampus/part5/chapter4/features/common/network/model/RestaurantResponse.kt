package fastcampus.part5.chapter4.features.common.network.model

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String, // Clementine
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("photograph")
    val photograph: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("latlng")
    val latlng: LatLngResponse,
    @SerializedName("cuisine_type")
    val cuisineType: String,
    @SerializedName("operating_hours")
    val operatingHours: OperatingHoursResponse,
    @SerializedName("reviews")
    val reviews: List<ReviewResponse>,
) {
    data class LatLngResponse(
        @SerializedName("lat")
        val lat: Float,
        @SerializedName("lng")
        val lng: Float,
    )

    data class OperatingHoursResponse(
        @SerializedName("Monday")
        val monday: String,
        @SerializedName("Tuesday")
        val tuesday: String,
        @SerializedName("Wednesday")
        val wednesday: String,
        @SerializedName("Thursday")
        val thursday: String,
        @SerializedName("Friday")
        val friday: String,
        @SerializedName("Saturday")
        val saturday: String,
        @SerializedName("Sunday")
        val sunday: String,
    )

    data class ReviewResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("rating")
        val rating: Int,
        @SerializedName("comments")
        val comments: String,
    )
}