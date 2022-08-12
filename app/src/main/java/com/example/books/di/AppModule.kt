package com.example.books.di

import android.content.Context
import androidx.room.Room
import com.example.books.common.Constants
import com.example.books.data.Remote.BookAPI
import com.example.books.data.Repository.BookRepositoryImpl
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.data.local.Dao.BookDao
import com.example.books.data.local.Dao.CategoryDao
import com.example.books.data.local.Db
import com.example.books.domain.repository.BookRepository
import com.example.books.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun BooksApi(): BookAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(api: BookAPI, categoryDao:CategoryDao): CategoryRepository {
        return CategoryRepositoryImpl(api,categoryDao)
    }
    @Provides
    @Singleton
    fun provideBookRepository(bookDao: BookDao): BookRepository {
        return BookRepositoryImpl(bookDao)
    }

    @Provides
    fun provideBookDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        Db::class.java,
        "db",
    ).build()

    @Provides
    fun provideBookDao(
        db:Db
    ) = db.bookDao()

    @Provides
    fun provideCategoryDao(
        db:Db
    ) = db.categoryDao()

}