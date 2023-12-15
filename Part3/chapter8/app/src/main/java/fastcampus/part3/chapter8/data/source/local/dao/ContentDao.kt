package fastcampus.part3.chapter8.data.source.local.dao

import androidx.room.*
import fastcampus.part3.chapter8.data.model.entity.ContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Query("SELECT * FROM Content ORDER BY createdDate DESC")
    fun selectAll(): Flow<List<ContentEntity>>

    @Delete
    suspend fun delete(item: ContentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ContentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ContentEntity>)
}