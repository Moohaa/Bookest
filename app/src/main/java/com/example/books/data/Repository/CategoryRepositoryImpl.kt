package com.example.books.data.Repository

import android.util.Log
import com.example.books.data.Remote.BookAPI
import com.example.books.data.Remote.DTO.CategoriesDTO
import com.example.books.data.Remote.DTO.toCategories
import com.example.books.domain.model.Category
import com.example.books.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: BookAPI
) : CategoryRepository {
    override suspend fun getCategories(): CategoriesDTO {
        Log.d("hh","jhkf"+api.getCategories().toCategories())
        return api.getCategories()
    }
}