package com.example.books.presentation.App.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.books.common.Ressource
import com.example.books.common.TempData
import com.example.books.data.Remote.DTO.Book
import com.example.books.data.Repository.BookRepositoryImpl
import com.example.books.domain.model.CategoryBooks
import com.example.books.domain.useCases.getCategories.GetCategoryBooksUseCase
import com.example.books.presentation.Stats.CategoryBooksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class  CategoryBooksViewModel @Inject constructor(
    private val getCategoryBooksUseCase: GetCategoryBooksUseCase,
    private val bookRepositoryImpl : BookRepositoryImpl
):ViewModel() {

    private val _state = mutableStateOf(CategoryBooksState())
    val state: State<CategoryBooksState> = _state

    fun getCategoryBooks(category_name: String) {
        if (_state.value.categoryBooks?.isEmpty() == true) {
            getCategoryBooksUseCase(category_name).onEach { result ->
                when (result) {
                    is Ressource.Success -> {
                        result.data?.let{
                            _state.value= CategoryBooksState(categoryBooks = result.data.data.books)
                            result.data.data.books.forEach{
                                TempData.data.add(it)
                            }
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
    }

}