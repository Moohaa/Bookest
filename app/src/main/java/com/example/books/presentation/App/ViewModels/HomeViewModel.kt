package com.example.books.presentation.App.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.common.Ressource
import com.example.books.common.TempData
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Repository.BookRepositoryImpl
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks
import com.example.books.domain.useCases.getCategories.GetCategoryBooksUseCase
import com.example.books.presentation.Stats.CategoriesState
import com.example.books.presentation.Stats.CategoryBooksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryBooksUseCase:  GetCategoryBooksUseCase,
    private val categoryRepositoryImpl: CategoryRepositoryImpl,
    private val bookRepositoryImpl: BookRepositoryImpl
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
                            TempData.data.add(it)
                        }
                        ll.categoryBooks=l
                        ll.isLoading=false
                        _state.value = ll
                        //_state.value= CategoryBooksState(categoryBooks = )
                    }
                }
                is Ressource.Error -> {
                    _state.value = CategoryBooksState(error = "network not issues")
                }
                is Ressource.Loading -> {
                    _state.value = CategoryBooksState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getFavCategories() {
        viewModelScope.launch {
            categoryRepositoryImpl.getLocalCategories().collect { response ->
                response.forEach{
                    if(it.isFav){
                        delay(500L)
                        getCategoryBooks(it.listNameEncoded)
                    }
                }

            }
        }
    }


}