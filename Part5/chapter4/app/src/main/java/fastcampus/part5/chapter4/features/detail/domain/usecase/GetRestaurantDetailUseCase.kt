package fastcampus.part5.chapter4.features.detail.domain.usecase

import fastcampus.part5.chapter4.features.common.entity.RestaurantDetailEntity
import fastcampus.part5.chapter4.features.common.repository.IRestaurantDataSource
import javax.inject.Inject

class GetRestaurantDetailUseCase @Inject constructor(
    private val dataSource: IRestaurantDataSource,
) : IGetRestaurantDetailUseCase {
    override suspend fun invoke(id: Int): RestaurantDetailEntity {
        return dataSource.getRestaurantDetail(id)
    }
}