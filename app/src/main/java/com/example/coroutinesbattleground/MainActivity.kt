package com.example.coroutinesbattleground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coroutinesbattleground.ui.screens.Home
import com.example.coroutinesbattleground.ui.screens.Option1

sealed class Routes(val route: String) {
    object Home : Routes("Coroutines Battleground")
    object Option : Routes("Option 1")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView()
        }
    }

    @Composable
    private fun ContentView() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            composable(Routes.Home.route) { Home(navController) }
            composable(Routes.Option.route) { Option1(navController) }
        }
    }


}