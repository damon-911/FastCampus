package fastcampus.part1.chapter7

import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT * FROM word ORDER BY id DESC")
    fun getAll() : List<Word>

    @Query("SELECT * FROM word ORDER BY id DESC LIMIT 1")
    fun getLatestWord() : Word

    @Insert
    fun insert(word: Word)

    @Delete
    fun delete(word: Word)

    @Update
    fun update(word: Word)
}