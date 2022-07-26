package com.example.coroutinesbattleground.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.coroutinesbattleground.Routes

@Composable
fun Option1(
    navController: NavController,
) = BaseOptionScreen(navController = navController, title = Routes.Option.route) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            Toast.makeText(navController.context, "Yay!", Toast.LENGTH_LONG).show()
        }) {
            Text(text = "Run me!")
        }
    }
}