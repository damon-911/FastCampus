package fastcampus.part5.chapter4.features.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.chapter4.features.common.network.api.IRestaurantAppNetworkApi
import fastcampus.part5.chapter4.features.common.network.api.RestaurantAppNetworkApi
import fastcampus.part5.chapter4.features.common.repository.IRestaurantDataSource
import fastcampus.part5.chapter4.features.common.repository.RestaurantRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RestaurantDataModule {

    @Singleton
    @Binds
    abstract fun bindRestaurantRepository(restaurantRepository: RestaurantRepository): IRestaurantDataSource

    @Singleton
    @Binds
    abstract fun bindNetwork(networkApi: RestaurantAppNetworkApi): IRestaurantAppNetworkApi
}