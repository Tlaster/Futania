package moe.tlaster.futania.common

import kotlinx.coroutines.*

fun runOnMainThread(action: () -> Unit) {
    GlobalScope.runOnMainThread(action)
}

fun runOnIOThread(action: () -> Unit) {
    GlobalScope.runOnIOThread(action)
}

fun runOnDefaultThread(action: () -> Unit) {
    GlobalScope.runOnDefaultThread(action)
}


fun CoroutineScope.runOnMainThread(action: () -> Unit) {
    launch {
        withContext(Dispatchers.Main) {
            action.invoke()
        }
    }
}

fun CoroutineScope.runOnIOThread(action: () -> Unit) {
    launch {
        withContext(Dispatchers.IO) {
            action.invoke()
        }
    }
}

fun CoroutineScope.runOnDefaultThread(action: () -> Unit) {
    launch {
        withContext(Dispatchers.Default) {
            action.invoke()
        }
    }
}


public inline fun async(noinline block: suspend () -> Unit): suspend () -> Unit = block

public fun <T> (suspend () -> T).fireAndForgot() {
    GlobalScope.launch {
        this@fireAndForgot.invoke()
    }
}