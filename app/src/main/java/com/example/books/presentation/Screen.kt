package com.example.books.presentation

sealed class Screen(val route :String){
    object RegistrationInfoscreen : Screen("RegistrationInfoScreen")
}
