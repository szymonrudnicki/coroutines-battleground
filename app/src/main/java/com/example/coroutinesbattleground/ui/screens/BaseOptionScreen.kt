package com.example.coroutinesbattleground.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BaseOptionScreen(
    navController: NavController,
    title: String,
    child: @Composable (NavController) -> Unit,
) = Scaffold(
    topBar = {
        TopAppBar(
            navigationIcon = { NavigationIcon(navController) },
            title = { Text(text = title) }
        )
    },
    content = {
        Surface(
            Modifier.fillMaxSize()
        ) { child(navController) }
    },
)

@Composable
private fun NavigationIcon(navController: NavController) {
    if (navController.previousBackStackEntry != null) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    } else {
        null
    }
}