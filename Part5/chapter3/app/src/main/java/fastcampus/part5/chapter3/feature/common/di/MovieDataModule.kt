package fastcampus.part5.chapter3.feature.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.chapter3.feature.common.network.api.IMovieAppNetworkApi
import fastcampus.part5.chapter3.feature.common.network.api.MovieAppNetworkApi
import fastcampus.part5.chapter3.feature.common.repository.IMovieDataSource
import fastcampus.part5.chapter3.feature.common.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDataModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): IMovieDataSource

    @Singleton
    @Binds
    abstract fun bindNetwork(networkApi: MovieAppNetworkApi): IMovieAppNetworkApi
}