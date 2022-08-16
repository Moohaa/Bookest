package com.example.books.data.Repository

import android.util.Log
import com.example.books.data.Remote.BookAPI
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Remote.DTO.CategoriesDTO
import com.example.books.data.Remote.DTO.CategoryBooksDTO
import com.example.books.data.Remote.DTO.toCategories
import com.example.books.data.local.Dao.CategoryDao
import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks
import com.example.books.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: BookAPI,
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override suspend fun getCategories(): CategoriesDTO {
        return api.getCategories()
    }

    override suspend fun getCategoryBooks(category_name:String): CategoryBooksDTO {
        return api.getCategoryBooks(category_name)
    }

    override suspend fun getLocalCategories(): Flow<List<Category>> {
        return categoryDao.getLocalCategories()
    }

    override suspend fun saveCategories(categories:List<Category>) {
        categories.forEach{
            categoryDao.addCategory(it)
        }
    }
}