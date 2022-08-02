package com.example.books.presentation.Stats

import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks

data class CategoryBooksState(
    val isLoading: Boolean = false,
    val categoryBooks: CategoryBooks?=null,
    val error: String = ""
)
