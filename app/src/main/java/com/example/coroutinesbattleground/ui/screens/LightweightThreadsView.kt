package com.example.coroutinesbattleground.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coroutinesbattleground.Routes
import kotlinx.coroutines.*
import kotlin.random.Random

@Composable
fun LightweightThreadsView(
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
                .fillMaxHeight(0.5f)
        ) {
            Text(
                coroutinesTextValue,
                Modifier
                    .verticalScroll(scroll)
                    .fillMaxHeight()
            )
        }

        Text("Coroutines are lightweight threads", fontWeight = FontWeight.Bold)

        var textValue by remember { mutableStateOf(TextFieldValue("500")) }
        val count = textValue.text.trim().toIntOrNull() ?: 0

        Surface(color = MaterialTheme.colors.surface, modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("How many?") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Button(onClick = {
            coroutinesTextValue = ""

            coroutineScope.launch {
                repeat(count) {
                    launch {
                        coroutinesTextValue += "${it + 1}\n "
                    }
                }
                delay(200L)
                scroll.animateScrollTo(scroll.maxValue - 1)
            }

        }) {
            Text(text = "Start coroutines with launch")
        }

        Button(onClick = {
            coroutinesTextValue = ""

            coroutineScope.launch {
                val asyncList = mutableListOf<Deferred<Unit>>()
                repeat(count) {
                    val async = async {
                        delay(Random.nextLong(100))
                        coroutinesTextValue += "${it + 1}\n "
                    }
                    asyncList.add(async)
                }

                asyncList.awaitAll()

                delay(200L)
                scroll.animateScrollTo(scroll.maxValue - 1)
            }

        }) {
            Text(text = "Start coroutines with async")
        }
    }
}