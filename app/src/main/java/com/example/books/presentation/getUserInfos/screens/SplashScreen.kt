package com.example.books.presentation.getUserInfos.screens

import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.books.R
import com.example.books.presentation.App.ViewModels.FavouriteViewModel
import com.example.books.presentation.getUserInfos.viewmodels.getStartedViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: getStartedViewModel = hiltViewModel()
){
    Column(
        modifier=Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        viewModel.setup(navController)
        Icon(
            modifier=Modifier.size(100.dp),
            painter = painterResource(id= R.drawable.ic_baseline_menu_book_24),
            contentDescription = null ,
            tint = MaterialTheme.colors.primary
        )
        Text(
            style = MaterialTheme.typography.h4,
            text = "Bookest"
        )
        CircularProgressIndicator(modifier = Modifier.padding(0.dp,80.dp))
        Text(
            text = "Powered by New York times API"
        )
    }


}