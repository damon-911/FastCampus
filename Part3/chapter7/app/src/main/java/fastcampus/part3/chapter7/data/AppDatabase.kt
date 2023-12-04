package fastcampus.part3.chapter7.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fastcampus.part3.chapter7.data.dao.ContentDao
import fastcampus.part3.chapter7.model.ContentEntity

@Database(entities = [ContentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contentDao(): ContentDao
}