package com.example.coroutinesbattleground.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coroutinesbattleground.Routes
import com.example.coroutinesbattleground.ui.MainViewModel

@Composable
fun JobView(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) = BaseOptionScreen(navController = navController, title = Routes.Job.route) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutinesTextValue = viewModel.textState
        val scroll = rememberScrollState()

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(0.8f)
        ) {
            Text(
                coroutinesTextValue,
                Modifier
                    .verticalScroll(scroll)
                    .fillMaxHeight()
            )
        }

        Text("Job can be used to cancel the coroutine", fontWeight = FontWeight.Bold)

        Button(onClick = {
            viewModel.launchJob()
        }) {
            Text("Launch")
        }

        Button(onClick = {
            viewModel.cancelJob()
        }) {
            Text("Cancel")
        }
    }
}
