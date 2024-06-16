package fastcampus.part5.chapter3.library.storage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.chapter3.library.storage.IStorage
import fastcampus.part5.chapter3.library.storage.StorageManager
import fastcampus.part5.chapter3.library.storage.helper.DataConverter
import fastcampus.part5.chapter3.library.storage.helper.DataEncoding
import fastcampus.part5.chapter3.library.storage.sharedprefs.SharedPrefsStorageProvider
import fastcampus.part5.chapter3.library.storage.sharedprefs.StorageProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun bindOnDiscStorage(
        storage: StorageProvider,
        converter: DataConverter,
        encoding: DataEncoding
    ): IStorage = StorageManager(storage, converter, encoding)

    @Provides
    fun provideSharePrefStorageProvider(provider: SharedPrefsStorageProvider): StorageProvider =
        provider

}
