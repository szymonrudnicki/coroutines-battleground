package com.example.coroutinesbattleground.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.coroutinesbattleground.Routes

@Composable
fun Home(navController: NavController) =
    BaseOptionScreen(navController = navController, title = Routes.Home.route) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate(Routes.Option.route) }) {
                Text(text = "First Option")
            }
        }
    }