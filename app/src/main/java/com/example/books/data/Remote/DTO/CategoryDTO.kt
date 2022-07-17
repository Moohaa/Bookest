package com.example.books.data.Remote.DTO


import com.example.books.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("list_name")
    val listName: String,
    @SerializedName("list_name_encoded")
    val listNameEncoded: String,
    @SerializedName("newest_published_date")
    val newestPublishedDate: String,
    @SerializedName("oldest_published_date")
    val oldestPublishedDate: String,
    val updated: String
)
fun CategoryDTO.toCategory():Category{
    return Category(
        displayName, listName, listNameEncoded,
        newestPublishedDate, oldestPublishedDate, updated
    )
}