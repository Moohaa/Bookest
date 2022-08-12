package com.example.books.presentation.getUserInfos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.books.presentation.App.AppGraph
import com.example.books.presentation.getUserInfos.screens.GetUserFavoriteCategories
import com.example.books.presentation.ui.theme.BooksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetUserInfoActivity :ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier=Modifier.fillMaxSize()){
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "getUserFavouritCategories") {
                        composable("getUserFavouritCategories"){ GetUserFavoriteCategories(navController) }
                        AppGraph()
                    }
                }
            }
        }
    }
}