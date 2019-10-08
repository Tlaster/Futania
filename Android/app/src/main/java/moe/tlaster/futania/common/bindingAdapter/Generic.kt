package moe.tlaster.futania.common.bindingAdapter

import android.view.View
import androidx.core.view.updatePaddingRelative
import androidx.databinding.BindingAdapter
import moe.tlaster.futania.common.dp


@BindingAdapter("paddingHorizontal")
fun paddingHorizontal(view: View, int: Int) {
    view.updatePaddingRelative(start = int.dp.toInt(), end = int.dp.toInt())
}

@BindingAdapter("paddingVertical")
fun paddingVertical(view: View, int: Int) {
    view.updatePaddingRelative(top = int.dp.toInt(), bottom = int.dp.toInt())
}