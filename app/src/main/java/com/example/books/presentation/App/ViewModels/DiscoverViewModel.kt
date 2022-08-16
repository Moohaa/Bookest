package com.example.books.presentation.App.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.data.Repository.BookRepositoryImpl
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.domain.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val categoryRepositoryImpl: CategoryRepositoryImpl,
) :ViewModel(){

    var categories by mutableStateOf(emptyList<Category>())

    init {
        getCategories()
    }
    fun getCategories() {
        viewModelScope.launch {
            categoryRepositoryImpl.getLocalCategories().collect { response ->
                categories = response

            }
        }
    }
}