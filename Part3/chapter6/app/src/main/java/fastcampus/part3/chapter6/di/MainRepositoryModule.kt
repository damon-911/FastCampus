package fastcampus.part3.chapter6.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import fastcampus.part3.chapter6.remote.MainService
import fastcampus.part3.chapter6.remote.repository.MainRepository
import fastcampus.part3.chapter6.remote.repository.MainRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object MainRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideMainRepository(
        mainService: MainService,
    ): MainRepository = MainRepositoryImpl(mainService)
}