package com.example.books.presentation.Stats

import com.example.books.data.Remote.DTO.Book
import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks

data class CategoryBooksState(
    var isLoading: Boolean = false,
    var categoryBooks: List<Book> = emptyList(),
    var error: String = ""
)
