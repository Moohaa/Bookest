package com.example.books.presentation.App

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.books.presentation.App.Screens.DiscoverScreen
import com.example.books.presentation.App.Screens.FavouriteScreen
import com.example.books.presentation.App.Screens.HomeScreen
import com.example.books.presentation.getUserInfos.screens.GetUserFavoriteCategories
import com.example.books.presentation.getUserInfos.screens.GetUserNameScreen

fun NavGraphBuilder.AppGraph() {
    navigation(startDestination = "app", route = "MainApp") {
        composable("app"){ AppScreen()}
    }
}