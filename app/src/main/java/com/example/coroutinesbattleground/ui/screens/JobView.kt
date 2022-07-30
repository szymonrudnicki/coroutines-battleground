package com.example.coroutinesbattleground.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coroutinesbattleground.Routes
import kotlinx.coroutines.*

@Composable
fun JobView(
    navController: NavController,
) = BaseOptionScreen(navController = navController, title = Routes.Job.route) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var coroutinesTextValue by remember { mutableStateOf("") }
        val scroll = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

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

        var job : Job? = null

        Button(onClick = {
            job = coroutineScope.launch(Dispatchers.Default) {
                var nextPrintTime = System.currentTimeMillis()
                while(isActive) {
                    yield()
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        coroutinesTextValue += "Working...\n"
                        nextPrintTime += 1000L
                    }
                }
                coroutinesTextValue += "Not active anymore.\n"
            }
        }) {
            Text("Launch")
        }

        Button(onClick = {
            job?.cancel()
        }) {
            Text("Cancel")
        }
    }
}
