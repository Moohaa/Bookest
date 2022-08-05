package com.example.books.presentation.App.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.books.domain.model.Category
import com.example.books.presentation.App.ViewModels.DiscoverViewModel
import com.example.books.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    navController: NavController,
    viewModel : DiscoverViewModel = hiltViewModel()

){
    val categories=viewModel.categories
    Column {
        LazyRow(){
            items(categories) { c ->
                if(c.isFav) FavCategoryCard(category = c)
                Divider()
            }
        }


        Row() {
            Text(
                modifier=Modifier.padding(20.dp,10.dp),
                style = MaterialTheme.typography.h5,
                text = "More Categories")
        }
        Divider(modifier= Modifier
            .padding(20.dp, 0.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(1f)
                .padding(10.dp, 0.dp)
        ) {
                items(categories) { c ->
                    if(!c.isFav){
                        CategoryCard(navController,category = c)
                        Divider(
                            modifier= Modifier
                                .padding(40.dp, 0.dp)
                                .fillMaxWidth()
                        )
                    }
                }


        }
    }
}
@Composable
fun FavCategoryCard(
    category: Category
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp, 10.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colors.secondary)
        .clickable {

        }
    ){
        Text(
            style=MaterialTheme.typography.h6,
            text = category.displayName,
            modifier = Modifier.padding(10.dp,30.dp)
        )
    }
}
@Composable
fun CategoryCard(
    navController: NavController,
    category: Category
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp, 5.dp))
        .padding(2.dp, 0.dp)
        .background(MaterialTheme.colors.background)
        .clickable {
            navController.navigate("book/55")
        }
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp, 15.dp,30.dp,15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                text = category.displayName,
                modifier = Modifier
                     .fillMaxWidth(0.8f)
            )
            Icon(
                painter = painterResource(id= R.drawable.ic_baseline_navigate_next_24),
                contentDescription = null // decorative element
            )

        }


    }
}