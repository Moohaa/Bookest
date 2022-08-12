package com.example.books.presentation.getUserInfos

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.books.presentation.getUserInfos.screens.GetUserFavoriteCategories

fun NavGraphBuilder.UserInfosGraph(navController: NavController) {
    navigation(startDestination = "getUserInfos", route = "userInfos") {
        composable("getUserFavouritCategories"){ GetUserFavoriteCategories(navController)}
    }
}
