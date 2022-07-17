package com.example.books.di

import com.example.books.common.Constants
import com.example.books.data.Remote.BookAPI
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideCategoriesRepository(api: BookAPI): CategoryRepository {
        return CategoryRepositoryImpl(api)
    }
}