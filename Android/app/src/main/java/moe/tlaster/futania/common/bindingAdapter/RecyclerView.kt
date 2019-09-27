package moe.tlaster.futania.common.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import moe.tlaster.futania.BR
import moe.tlaster.futania.common.DataBindingAdapter
import moe.tlaster.futania.common.ItemSelector


@BindingAdapter("itemsSource", "itemTemplate", requireAll = true)
fun <T> adapter(recyclerView: RecyclerView, list: List<T>, layoutId: Int) {
    val onClick = recyclerView.getTag(ITEMCLICK_ID) as? (T) -> Unit
    recyclerView.adapter = DataBindingAdapter<T>(ItemSelector(layoutId, BR.model)).also { adapter ->
        adapter.items = list
        onClick?.let {
            adapter.onClick = it
        }
    }
}


private val ITEMCLICK_ID = -4590
@BindingAdapter("itemClicked")
fun <T> itemClicked(recyclerView: RecyclerView, action: (T) -> Unit) {
    val adapter = recyclerView.adapter?.let {
        it as? DataBindingAdapter<T>
    }
    if (adapter != null) {
        adapter.onClick = action
    } else {
        recyclerView.setTag(ITEMCLICK_ID, action)
    }
}
