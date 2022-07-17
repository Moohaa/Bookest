package com.example.books.domain.repository

import com.example.books.data.Remote.DTO.CategoriesDTO
import com.example.books.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories():CategoriesDTO
    //fun getCategoryBooks():List<Books>
}