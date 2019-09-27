package moe.tlaster.futania.common.bindingAdapter

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter("onMenuClicked")
fun onMenuClicked(toolbar: Toolbar, action: (MenuItem) -> Boolean) {
    toolbar.setOnMenuItemClickListener(action)
}