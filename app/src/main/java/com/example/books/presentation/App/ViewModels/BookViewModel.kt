package com.example.books.presentation.App.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Repository.BookRepositoryImpl
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.domain.model.Category
import com.example.books.domain.useCases.getCategories.GetCategoryBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepositoryImpl: BookRepositoryImpl
): ViewModel(){

    fun saveBook(book:Book){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepositoryImpl.saveBook(book)
        }
    }

}