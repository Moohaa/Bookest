package com.example.books.presentation.App.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.presentation.App.ViewModels.FavouriteViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel :FavouriteViewModel = hiltViewModel()
){
    viewModel.getFavBooks()
    val books=viewModel.books

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp,10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
            items(books) { c ->
                    Image(
                        painter = rememberAsyncImagePainter(c.bookImage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(270.dp)
                            .padding(0.dp, 4.dp)
                            .clickable {
                                navController.navigate("book/"+c.title)
                            }
                    )
            }

    }

}