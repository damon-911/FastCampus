package fastcampus.part3.chapter8.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fastcampus.part3.chapter8.data.source.local.dao.ContentDao
import fastcampus.part3.chapter8.data.model.entity.ContentEntity

@Database(entities = [ContentEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contentDao(): ContentDao
}