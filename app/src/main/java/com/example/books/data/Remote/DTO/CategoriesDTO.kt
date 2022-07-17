package com.example.books.data.Remote.DTO


import com.example.books.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoriesDTO(
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    val results: List<CategoryDTO>,
    val status: String
)

fun CategoriesDTO.toCategories():List<Category>{
    return results.map { it->
        it.toCategory()
    }
}