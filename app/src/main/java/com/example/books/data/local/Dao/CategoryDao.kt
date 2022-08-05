package com.example.books.data.local.Dao

import androidx.room.*
import com.example.books.data.Remote.DTO.Book
import com.example.books.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category ORDER BY displayName ASC")
    fun getLocalCategories(): Flow<List<Category>>

    @Query("SELECT * FROM Category WHERE displayName = :name")
    fun getCategory(name: Int): Flow<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCategory(category: Category)

}