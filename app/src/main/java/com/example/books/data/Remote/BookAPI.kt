package com.example.books.data.Remote

import com.example.books.data.Remote.DTO.CategoriesDTO
import com.example.books.data.Remote.DTO.CategoryBooksDTO
import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks
import retrofit2.http.GET
import retrofit2.http.Path

interface BookAPI {

    @GET("/svc/books/v3/lists/names.json?api-key=uo3VLCyCqdkGFq3TxcLoyZuvQACVoEsp")
    suspend fun getCategories() :CategoriesDTO

    @GET("https://api.nytimes.com/svc/books/v3/lists/current/{category_name}.json?api-key=uo3VLCyCqdkGFq3TxcLoyZuvQACVoEsp")
    suspend fun  getCategoryBooks(@Path("category_name") category_name: String,):CategoryBooksDTO
}