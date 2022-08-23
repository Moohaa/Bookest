package com.example.books.data.Repository

import android.util.Log
import com.example.books.data.Remote.BookAPI
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.local.Dao.BookDao
import com.example.books.data.local.Dao.CategoryDao
import com.example.books.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl  @Inject constructor(
    private val bookDao: BookDao
) : BookRepository{
    override suspend fun getBook(book_id: String): Flow<Book> {
        return bookDao.getBook(book_id)
    }
    override suspend fun saveBook(book: Book) {
        bookDao.addBook(book)
    }
    override suspend fun getBooks(): Flow<List<Book>> {
        return bookDao.getBooks()
    }

    override suspend fun deleteBook(book :Book) {
        return bookDao.deleteBook(book)
    }
}