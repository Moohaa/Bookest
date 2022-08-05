package com.example.books.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Category")
data class Category(
    @PrimaryKey
    val displayName: String,

    val listName: String,
    val listNameEncoded: String,
    val newestPublishedDate: String,
    val oldestPublishedDate: String,
    val updated: String,
    var isFav:Boolean=false
)
