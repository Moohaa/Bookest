package com.example.books.presentation.App

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.books.R

sealed class BottomNavItem(var title:String, var icon:Int, var route:String){
    object Home : BottomNavItem("Home", R.drawable.ic_baseline_home_24,"home")
    object Discover : BottomNavItem("Discover", R.drawable.ic_baseline_search_24,"discover")
    object Favourite : BottomNavItem("Favourite", R.drawable.ic_baseline_favorite_24,"favourite")
}