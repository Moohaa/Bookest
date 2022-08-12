package com.example.books.presentation.App.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.books.data.Remote.DTO.Book
import com.example.books.presentation.App.ViewModels.BookViewModel


@Composable
fun Topbar(
    navController: NavController,
    viewModel: BookViewModel,
    book: Book
){
    TopAppBar(
        modifier=Modifier.background(Color.Transparent),
        title = { Text(text = "") },
        navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        },
        actions = {
            var clicked by remember {
                mutableStateOf(false)
            }
            IconButton(onClick = {
                viewModel.saveBook(book)
                clicked=true
            }) {
                Icon(
                    modifier =Modifier
                        .padding(15.dp)
                        ,
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Back",
                    tint = if(clicked) Color.Red else MaterialTheme.colors.onBackground
                )
            }



        }
    )
}