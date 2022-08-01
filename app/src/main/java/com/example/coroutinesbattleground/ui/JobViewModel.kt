package com.example.coroutinesbattleground.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class JobViewModel : ViewModel() {

    private var job: Job? = null

    var textState by mutableStateOf("")
        private set

    fun launchJob() {
        job = viewModelScope.launch(Dispatchers.Default) {
            var nextPrintTime = System.currentTimeMillis()
            while (isActive) {
                yield()
                if (System.currentTimeMillis() >= nextPrintTime) {
                    textState += "Working...\n"
                    nextPrintTime += 1000L
                }
            }
        }
    }

    fun cancelJob() {
        viewModelScope.launch {
            job?.let {
                it.cancelAndJoin()
                textState += "Not active anymore. IsActive = ${it.isActive}\n"
            }
        }
    }

}