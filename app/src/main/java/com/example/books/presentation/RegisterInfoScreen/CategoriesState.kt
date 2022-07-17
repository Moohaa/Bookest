package com.example.books.presentation.RegisterInfoScreen

import com.example.books.domain.model.Category

data class CategoriesState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)