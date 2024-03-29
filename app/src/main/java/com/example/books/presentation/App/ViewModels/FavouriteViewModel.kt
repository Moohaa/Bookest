package com.example.books.presentation.App.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.common.TempData
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Repository.BookRepositoryImpl
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.domain.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val bookRepositoryImpl: BookRepositoryImpl
) : ViewModel(){

    var books by mutableStateOf(emptyList<Book>())
    var call=false

    init{
        viewModelScope.launch {
            bookRepositoryImpl.getBooks().collect { response ->
                response.forEach{
                    TempData.data.add(it)
                }

            }
        }
    }

    fun  getFavBooks() {
        if(!call){
            call=true
            viewModelScope.launch {
                bookRepositoryImpl.getBooks().collect { response ->
                    books=response
                    call=false
                }
            }
        }
    }

}