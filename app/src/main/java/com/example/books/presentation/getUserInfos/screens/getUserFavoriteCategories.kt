package com.example.books.presentation.getUserInfos.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.books.presentation.getUserInfos.viewmodels.GetUserFavCategoriesViewModel

@Composable
fun GetUserFavoriteCategories(
    navController: NavController,
    viewModel:GetUserFavCategoriesViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding((20.dp))) {
        Column(
            modifier=Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Your Favourite Categories", modifier = Modifier
                    .padding(10.dp),
                    style=MaterialTheme.typography.h5,
                    color=MaterialTheme.colors.primary
                )
            }
            Divider(modifier = Modifier.background(MaterialTheme.colors.primary))
            LazyColumn(modifier = Modifier.fillMaxHeight(0.9f)
            ) {
                items(state.categories) { c ->
                    var isselected by remember {
                        mutableStateOf(false)
                    }
                    println(c.toString())
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isselected = !isselected
                            }
                            .background(if (isselected) MaterialTheme.colors.secondary else Color.Transparent)

                            .padding(0.dp, 10.dp)
                    )
                    {
                        Text(
                            text = c.displayName,
                            modifier = Modifier.padding(10.dp,0.dp)
                        )
                    }
                    Divider()
                }
            }
            Divider(modifier = Modifier.background(MaterialTheme.colors.primary))
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    modifier=Modifier.width(150.dp),
                    onClick = {
                        navController.navigate("app"){
                            popUpTo(0)
                        }
                }) {
                    Text(text = "Next")
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

