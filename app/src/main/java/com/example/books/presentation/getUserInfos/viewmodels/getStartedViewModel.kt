package com.example.books.presentation.getUserInfos.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.books.data.Repository.CategoryRepositoryImpl
import com.example.books.domain.model.Category
import com.example.books.domain.useCases.getCategories.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class getStartedViewModel @Inject constructor(
    private val categoryRepositoryImpl: CategoryRepositoryImpl
)  : ViewModel() {

    private var call=false;
    fun setup(navController:NavController){
        if(!call){
            viewModelScope.launch {
                call=true
                categoryRepositoryImpl.getLocalCategories().collect { response ->
                    delay(1000L)
                    if(response.isEmpty()) navController.navigate("getUserFavouritCategories")
                    else navController.navigate("app"){
                        popUpTo(0)
                    }
                }
            }
        }

    }

}