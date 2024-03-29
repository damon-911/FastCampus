package fastcampus.part3.chapter8.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part3.chapter8.data.source.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun providesContentDao(appDatabase: AppDatabase) = appDatabase.contentDao()
}