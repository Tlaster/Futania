package moe.tlaster.futania.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun runOnMainThread(action: () -> Unit) {
    GlobalScope.launch {
        withContext(Dispatchers.Main) {
            action.invoke()
        }
    }
}