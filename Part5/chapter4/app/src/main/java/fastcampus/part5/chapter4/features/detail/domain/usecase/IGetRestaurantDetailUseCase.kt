package fastcampus.part5.chapter4.features.detail.domain.usecase

import fastcampus.part5.chapter4.core.entity.RestaurantDetailEntity

interface IGetRestaurantDetailUseCase {
    suspend operator fun invoke(id: Int): RestaurantDetailEntity
}