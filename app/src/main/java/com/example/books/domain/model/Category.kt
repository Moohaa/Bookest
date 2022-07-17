package com.example.books.domain.model


data class Category(
    val displayName: String,
    val listName: String,
    val listNameEncoded: String,
    val newestPublishedDate: String,
    val oldestPublishedDate: String,
    val updated: String
)
