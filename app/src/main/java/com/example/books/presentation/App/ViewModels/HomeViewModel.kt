package com.example.books.presentation.App.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.common.Ressource
import com.example.books.domain.model.CategoryBooks
import com.example.books.domain.useCases.getCategories.GetCategoryBooksUseCase
import com.example.books.presentation.Stats.CategoriesState
import com.example.books.presentation.Stats.CategoryBooksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryBooksUseCase:  GetCategoryBooksUseCase
):ViewModel(){
    private val _state = mutableStateOf(CategoryBooksState())
    val state: State<CategoryBooksState> = _state

    init {
        getCategoryBooks("hardcover-fiction")
    }
    private fun getCategoryBooks(category_name:String) {
        getCategoryBooksUseCase(category_name).onEach { result ->
            when (result) {
                is Ressource.Success -> {
                    result.data?.let{
                        _state.value = CategoryBooksState(categoryBooks =result.data)
                        Log.d("hh", result.data.toString())
                    }
                }
                is Ressource.Error -> {
                    _state.value = CategoryBooksState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Ressource.Loading -> {
                    _state.value = CategoryBooksState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}