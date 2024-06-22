package fastcampus.part5.chapter4.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.chapter4.libraries.network.retrofit.NetworkRequestFactoryImpl
import fastcampus.part5.chapter4.libraries.network_contract.api.NetworkRequestFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun bindNetworkRequestFactory(networkRequestFactory: NetworkRequestFactoryImpl): NetworkRequestFactory =
        networkRequestFactory
}