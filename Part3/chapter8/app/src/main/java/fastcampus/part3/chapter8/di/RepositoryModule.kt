package fastcampus.part3.chapter8.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import fastcampus.part3.chapter8.domain.repository.ContentRepository
import fastcampus.part3.chapter8.data.repository.ContentRepositoryImpl
import fastcampus.part3.chapter8.data.source.local.dao.ContentDao
import fastcampus.part3.chapter8.data.source.remote.api.ContentService

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(
        contentDao: ContentDao,
        contentService: ContentService
    ) : ContentRepository = ContentRepositoryImpl(contentDao, contentService)
}