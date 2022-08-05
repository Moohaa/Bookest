package com.example.books.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Remote.DTO.Converters.BuyLinksConverter
import com.example.books.data.local.Dao.BookDao
import com.example.books.data.local.Dao.CategoryDao
import com.example.books.domain.model.Category

@Database(entities = [Category::class,Book::class], version = 1, exportSchema = false)
@TypeConverters(BuyLinksConverter::class)
abstract class Db:RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun categoryDao(): CategoryDao
}