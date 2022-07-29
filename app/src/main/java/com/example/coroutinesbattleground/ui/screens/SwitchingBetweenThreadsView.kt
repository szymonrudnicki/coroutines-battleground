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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val threadName get() = Thread.currentThread().name

@Composable
fun SwitchingBetweenThreadsView(
    navController: NavController,
) = BaseOptionScreen(navController = navController, title = Routes.LightweightThreads.route) {
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

        Text("Coroutines can jump to different threads", fontWeight = FontWeight.Bold)

        Button(onClick = {
            coroutineScope.launch {
                withContext(Dispatchers.Default) {
                    coroutinesTextValue += "I'm on '${threadName}' thread (default)\n"
                }
                delay(1500L)
                withContext(Dispatchers.Main) {
                    coroutinesTextValue += "Entering '${threadName}' thread\n"
                    delay(1500L)
                    withContext(Dispatchers.IO) {
                        coroutinesTextValue += "I'm on '${threadName}' thread (IO) \n"
                        delay(1500L)
                    }
                    coroutinesTextValue += "I'm back on '${threadName}' thread\n"
                }
                delay(1500L)
            }
        }) {
            Text("Run")
        }
    }
}
