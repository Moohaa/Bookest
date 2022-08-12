package com.example.books.domain.repository

import com.example.books.data.Remote.DTO.Book
import com.example.books.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBook(book_id : String): Flow<Book>
    suspend fun saveBook(book :Book):Unit

    suspend fun getFavBooks() : Flow<List<Book>>
}