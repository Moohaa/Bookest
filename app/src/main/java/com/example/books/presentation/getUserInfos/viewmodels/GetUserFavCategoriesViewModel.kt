package com.example.books.presentation.getUserInfos.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.common.Ressource
import com.example.books.domain.useCases.getCategories.GetCategoriesUseCase
import com.example.books.presentation.Stats.CategoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GetUserFavCategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
)  : ViewModel() {
    private val _state = mutableStateOf(CategoriesState())
    val state: State<CategoriesState> = _state

    init {
        getCategories()

    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Ressource.Success -> {
                    _state.value = CategoriesState(categories = result.data ?: emptyList())
                }
                is Ressource.Error -> {
                    _state.value = CategoriesState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Ressource.Loading -> {
                    _state.value = CategoriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}

