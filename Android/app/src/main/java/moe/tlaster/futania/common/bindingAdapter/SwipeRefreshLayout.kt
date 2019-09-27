package moe.tlaster.futania.common.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moe.tlaster.futania.common.runOnIOThread
import moe.tlaster.futania.common.runOnMainThread

@BindingAdapter("onRefresh")
fun onRefresh(swipeRefreshLayout: SwipeRefreshLayout, action: suspend () -> Unit) {
    val refreshAction = {
        GlobalScope.launch {
            runOnMainThread {
                swipeRefreshLayout.isRefreshing = true
            }
            action.invoke()
            runOnMainThread {
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    swipeRefreshLayout.setOnRefreshListener {
        refreshAction.invoke()
    }
    refreshAction.invoke()//initial refresh
}

