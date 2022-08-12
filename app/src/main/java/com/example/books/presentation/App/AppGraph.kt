package com.example.books.presentation.App

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.AppGraph() {
    navigation(startDestination = "app", route = "MainApp") {
        composable("app"){ AppScreen()}
    }
}