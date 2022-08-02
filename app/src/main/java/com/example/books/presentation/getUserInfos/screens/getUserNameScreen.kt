package com.example.books.presentation.getUserInfos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.intellij.lang.annotations.JdkConstants

@Composable
fun GetUserNameScreen(
    navController: NavController
){

    var nameState by  remember {
        mutableStateOf("")
    }
    var userName by remember {
        mutableStateOf("")

    }
        Box(
            modifier=Modifier.fillMaxSize()
                .padding(20.dp)

        ){
            Column(
                modifier=Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    modifier=Modifier.fillMaxWidth(),
                    value = nameState, onValueChange ={
                    nameState=it
                } )
                Row(

                    horizontalArrangement = Arrangement.End,
                    modifier=Modifier
                        .fillMaxWidth()
                ){
                    Button(onClick = {
                        userName=nameState
                        navController.navigate("getUserFavouritCategories")
                    }) {
                        Text(text = "Next")
                    }

                }

            }

    }


}