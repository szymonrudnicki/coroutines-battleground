package com.example.coroutinesbattleground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coroutinesbattleground.ui.screens.Home
import com.example.coroutinesbattleground.ui.screens.JobView
import com.example.coroutinesbattleground.ui.screens.LightweightThreadsView
import com.example.coroutinesbattleground.ui.screens.SwitchingBetweenThreadsView

sealed class Routes(val route: String) {
    object Home : Routes("Coroutines Battleground")
    object LightweightThreads : Routes("Lightweight threads")
    object SwitchingBetweenThreads : Routes("Switching between threads")
    object Job : Routes("Job")
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

            composable(Routes.LightweightThreads.route) { LightweightThreadsView(navController) }
            composable(Routes.SwitchingBetweenThreads.route) { SwitchingBetweenThreadsView(navController) }
            composable(Routes.Job.route) { JobView(navController) }
        }
    }


}