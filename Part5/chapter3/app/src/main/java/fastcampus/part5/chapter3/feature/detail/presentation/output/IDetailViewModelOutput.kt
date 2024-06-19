package fastcampus.part5.chapter3.feature.detail.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IDetailViewModelOutput {
    val detailState: StateFlow<DetailState>
    val detailUiEffect: SharedFlow<DetailUiEffect>
}

sealed class DetailUiEffect {
    object Back : DetailUiEffect()

    data class OpenUrl(
        val url: String
    ) : DetailUiEffect()

    data class RateMovie(
        val movieTitle: String,
        val rating: Float
    ) : DetailUiEffect()
}