package com.example.books.presentation.App.Screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.presentation.App.ViewModels.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(text = "This week", style = MaterialTheme.typography.h5,
                modifier= Modifier.padding(20.dp,10.dp)
            )
        }
        Divider(modifier= Modifier
            .padding(20.dp, 0.dp)
            .fillMaxWidth()
            .background(Color.Black) )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(0.dp,10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            state.categoryBooks?.data?.let {
                items(it.books) { c ->
                        Image(
                            painter = rememberAsyncImagePainter(c.bookImage),
                            contentDescription = null,
                            modifier = Modifier
                                .size(270.dp)
                                .padding(0.dp, 4.dp)
                                .clickable { TODO() }
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
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator()
        }



    }

}