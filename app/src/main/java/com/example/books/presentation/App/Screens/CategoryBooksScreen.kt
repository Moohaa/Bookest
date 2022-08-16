package com.example.books.presentation.App.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.presentation.App.Components.Topbar
import com.example.books.presentation.App.ViewModels.CategoryBooksViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryBooksScreen(
    navController: NavController,
    viewModel : CategoryBooksViewModel = hiltViewModel(),
    categoryName:String
){
    viewModel.getCategoryBooks(categoryName)

    var state=viewModel.state.value

    Scaffold(
        topBar = {
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
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(0.dp,10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    state.categoryBooks?.let{
                        items(it.data.books) { c ->
                            Image(
                                painter = rememberAsyncImagePainter(c.bookImage),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(270.dp)
                                    .padding(0.dp, 4.dp)
                                    .clickable {
                                        navController.navigate("book/" + c.title)
                                    }
                            )

                        }
                    }
                }
                if(state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if(state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

            }
        }
    )
}
