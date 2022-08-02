package com.example.books.presentation.App.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.presentation.App.BottomNavItem
import com.example.books.presentation.App.ViewModels.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    navController: NavController,
    viewModel :HomeViewModel= hiltViewModel()

){
    val state = viewModel.state.value





}