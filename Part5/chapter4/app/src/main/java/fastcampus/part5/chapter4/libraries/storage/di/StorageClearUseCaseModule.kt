package fastcampus.part5.chapter4.libraries.storage.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.chapter4.libraries.storage.usecase.IStorageClearUseCase
import fastcampus.part5.chapter4.libraries.storage.usecase.StorageClearUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageClearUseCaseModule {

    @Binds
    @Singleton
    abstract fun bindStorageClearUseCase(
        storageClearUserCase: StorageClearUseCase,
    ): IStorageClearUseCase
}
