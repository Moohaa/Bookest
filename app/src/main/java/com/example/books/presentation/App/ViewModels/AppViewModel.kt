package com.example.books.presentation.App.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Repository.BookRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val bookRepositoryImpl: BookRepositoryImpl

) :ViewModel(){
    var  books :List<Book> = emptyList()

    init{
        getBooks()
    }

    fun getBooks(){
        viewModelScope.launch{
            bookRepositoryImpl.getBooks().collect{
                books= it
            }
        }
    }
}