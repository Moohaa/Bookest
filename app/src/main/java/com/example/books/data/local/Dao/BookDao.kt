package com.example.books.data.local.Dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.books.data.Remote.DTO.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM Book ORDER BY title ASC")
    fun getBooks(): Flow<List<Book>>

    @Query("SELECT * FROM Book WHERE title = :book_title")
    fun getBook(book_title: String): Flow<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}