package com.example.books.presentation.App.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.common.Ressource
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks
import com.example.books.domain.useCases.getCategories.GetCategoryBooksUseCase
import com.example.books.presentation.Stats.CategoriesState
import com.example.books.presentation.Stats.CategoryBooksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryBooksUseCase:  GetCategoryBooksUseCase,
    private val categoryRepositoryImpl: CategoryRepositoryImpl
):ViewModel(){
    private val _state = mutableStateOf(CategoryBooksState())
    val state: State<CategoryBooksState> = _state

    var l:MutableList<Book> = mutableListOf()
    var ll=CategoryBooksState()



    init {
        getFavCategories()
    }
    private fun getCategoryBooks(category_name:String) {
        getCategoryBooksUseCase(category_name).onEach { result ->
            when (result) {
                is Ressource.Success -> {
                    result.data?.let{
                        Log.d("j",result.data.toString()+"\n")
                        result.data.data.books.forEach{
                            l.add(it)
                        }
                        ll.categoryBooks=l
                        _state.value = ll
                    }
                }
                is Ressource.Error -> {
                    ll.error="hh"
                    _state.value = ll
                }
                is Ressource.Loading -> {
                    ll.isLoading=true
                    _state.value = ll
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getFavCategories() {
        viewModelScope.launch {
            categoryRepositoryImpl.getLocalCategories().collect { response ->
                response.forEach{
                    if(it.isFav){
                        getCategoryBooks(it.listNameEncoded)
                    }
                }

            }
        }
    }

}