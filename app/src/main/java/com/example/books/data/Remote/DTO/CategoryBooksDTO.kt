package com.example.books.data.Remote.DTO

import com.example.books.domain.model.CategoryBooks
import com.google.gson.annotations.SerializedName


data class CategoryBooksDTO(
    val copyright: String,
    @SerializedName("last_modified")
    val lastModified: String,
    @SerializedName("num_results")
    val numResults: Int,
    val results: Results,
    val status: String
)

fun CategoryBooksDTO.toCategoryBooks(): CategoryBooks{
    return CategoryBooks(results)
}