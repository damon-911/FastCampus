package fastcampus.part5.chapter4.features.detail.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.chapter4.features.detail.domain.usecase.GetRestaurantDetailUseCase
import fastcampus.part5.chapter4.features.detail.domain.usecase.IGetRestaurantDetailUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetRestaurantDetailUseCase(getRestaurantDetailUseCase: GetRestaurantDetailUseCase): IGetRestaurantDetailUseCase
}