package fastcampus.part5.chapter4.features.detail.presentation.output

import fastcampus.part5.chapter4.core.entity.RestaurantDetailEntity

sealed class DetailState {
    object Initial: DetailState()
    class Main(val detailEntity: RestaurantDetailEntity): DetailState()
}