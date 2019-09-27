package moe.tlaster.futania.common

import kotlinx.coroutines.*

fun runOnMainThread(action: () -> Unit) {
    GlobalScope.launch {
        withContext(Dispatchers.Main) {
            action.invoke()
        }
    }
}

fun runOnIOThread(action: () -> Unit) {
    GlobalScope.launch {
        withContext(Dispatchers.IO) {
            action.invoke()
        }
    }
}

fun runOnDefaultThread(action: () -> Unit) {
    GlobalScope.launch {
        withContext(Dispatchers.Default) {
            action.invoke()
        }
    }
}


public inline fun async(noinline block: suspend () -> Unit): suspend () -> Unit = block