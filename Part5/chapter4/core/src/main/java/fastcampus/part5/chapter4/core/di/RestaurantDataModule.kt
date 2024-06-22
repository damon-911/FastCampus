package fastcampus.part5.chapter4.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.chapter4.core.network.api.IRestaurantAppNetworkApi
import fastcampus.part5.chapter4.core.network.api.RestaurantAppNetworkApi
import fastcampus.part5.chapter4.core.repository.IRestaurantDataSource
import fastcampus.part5.chapter4.core.repository.RestaurantRepository
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