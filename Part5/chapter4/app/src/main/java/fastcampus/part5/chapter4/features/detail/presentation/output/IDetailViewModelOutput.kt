package fastcampus.part5.chapter4.features.detail.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IDetailViewModelOutput {
    val detailState: StateFlow<DetailState>
    val detailUiEffect: SharedFlow<DetailUiEffect>
}

sealed class DetailUiEffect {
    object Back: DetailUiEffect()

    data class OpenUrl(
        val url: String
    ): DetailUiEffect()

    data class RateRestaurant(
        val restaurantName: String,
        val rating: Float
    ): DetailUiEffect()
}