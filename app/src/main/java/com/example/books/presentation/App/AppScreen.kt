package com.example.books.presentation.App

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.books.presentation.App.Screens.*
import com.example.books.presentation.App.ViewModels.AppViewModel
import com.example.books.presentation.App.ViewModels.BookViewModel
import com.example.books.presentation.App.ViewModels.HomeViewModel

@Composable
fun AppScreen(
    viewModel: AppViewModel = hiltViewModel()
){
    val navController = rememberNavController()
    val items= listOf(
        BottomNavItem.Home,
        BottomNavItem.Discover,
        BottomNavItem.Favourite
    )

    Scaffold(

        bottomBar = {
            val s=navController
                .currentBackStackEntryAsState().value?.destination?.route
            if(listOf<String>("home","discover","favourite").contains(s)){
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(screen.icon), contentDescription = null) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }

            }

        }
    ) { innerPadding ->
        NavHost(navController, startDestination = BottomNavItem.Home.route, Modifier.padding(innerPadding)) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(BottomNavItem.Discover.route) { DiscoverScreen(navController) }
            composable(BottomNavItem.Favourite.route) { FavouriteScreen(navController)}
            composable("book/{book_title}") {backStackEntry->

                val book_id= backStackEntry.arguments?.getString("book_title")
                viewModel.getBooks()
                viewModel.books.forEach{
                    if(it.title == book_id){
                        BookScreen(navController,it)
                    }
                }

            }
            composable("category/{category_name}"){ backStackEntry->
                val category_name= backStackEntry.arguments?.getString("category_name")
                CategoryBooksScreen(navController = navController, categoryName = category_name!!)

            }
        }
    }

}