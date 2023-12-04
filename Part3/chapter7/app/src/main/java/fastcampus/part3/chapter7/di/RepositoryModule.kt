package fastcampus.part3.chapter7.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import fastcampus.part3.chapter7.data.dao.ContentDao
import fastcampus.part3.chapter7.repository.ContentRepository
import fastcampus.part3.chapter7.repository.ContentRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesContentRepository(contentDao: ContentDao): ContentRepository =
        ContentRepositoryImpl(contentDao)
}