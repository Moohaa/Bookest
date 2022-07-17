package com.example.books.data.Remote

import com.example.books.data.Remote.DTO.CategoriesDTO
import com.example.books.domain.model.Category
import retrofit2.http.GET

interface BookAPI {

    @GET("/svc/books/v3/lists/names.json?api-key=uo3VLCyCqdkGFq3TxcLoyZuvQACVoEsp")
    suspend fun getCategories() :CategoriesDTO

}