package fastcampus.part3.chapter8.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "Content")
data class ContentEntity(
    @PrimaryKey(false)
    val id: Int,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var content: String,
    @ColumnInfo
    var category: String,
    @ColumnInfo
    val createdDate: Date,
    @ColumnInfo
    val likeCount: Int,
    @ColumnInfo
    val commentCount: Int,
    @ColumnInfo
    val viewCount: Int,
) : Serializable