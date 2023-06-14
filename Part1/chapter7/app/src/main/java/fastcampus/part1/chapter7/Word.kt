package fastcampus.part1.chapter7

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "word")
data class Word(
    val word : String,
    val mean : String,
    val type : String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable
