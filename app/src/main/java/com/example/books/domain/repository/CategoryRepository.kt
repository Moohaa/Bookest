package com.example.books.domain.repository

import com.example.books.data.Remote.DTO.CategoriesDTO
import com.example.books.data.Remote.DTO.CategoryBooksDTO
import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories():CategoriesDTO
    suspend  fun getCategoryBooks(category_name:String):CategoryBooksDTO

    suspend fun getLocalCategories(): Flow<List<Category>>
    suspend fun saveCategories(categories:List<Category>):Unit
}