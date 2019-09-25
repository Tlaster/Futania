package moe.tlaster.futania.common.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager2.widget.ViewPager2
import moe.tlaster.futania.BR
import moe.tlaster.futania.common.DataBindingAdapter
import moe.tlaster.futania.common.ItemSelector


@BindingAdapter("itemsSource", "itemTemplate", requireAll = true)
fun <T> items(viewPager2: ViewPager2, list: List<T>, layoutId: Int) {
    val onClick = viewPager2.getTag(ITEMCLICK_ID) as? (T) -> Unit
    viewPager2.adapter = DataBindingAdapter<T>(ItemSelector(layoutId, BR.model)).also { adapter ->
        adapter.items = list
        onClick?.let {
            adapter.onClick = it
        }
    }
}

private val ITEMCLICK_ID = -4590
@BindingAdapter("itemClicked")
fun <T> itemClicked(viewPager2: ViewPager2, action: (T) -> Unit) {
    val adapter = viewPager2.adapter?.let {
        it as? DataBindingAdapter<T>
    }
    if (adapter != null) {
        adapter.onClick = action
    } else {
        viewPager2.setTag(ITEMCLICK_ID, action)
    }
}


@BindingAdapter("autoSwitch")
fun autoSwitch(viewPager2: ViewPager2, value: Boolean) {
    if (!value) {
        return
    }
    viewPager2.context.let {
        it as? LifecycleOwner
    }?.lifecycle?.also {
        val observer = object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResume() {
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPause() {
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                it.removeObserver(this)
            }
        }
        it.addObserver(observer)
    }
}