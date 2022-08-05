package com.example.books.data.local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.books.data.Remote.DTO.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM Book ORDER BY id ASC")
    fun getBooks(): Flow<List<Book>>

    @Query("SELECT * FROM Book WHERE id = :id")
    fun getBook(id: Int): Flow<Book>

    @Insert(onConflict = IGNORE)
    fun addBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}