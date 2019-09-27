package moe.tlaster.futania.common.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("source", "circle", requireAll = false)
fun source(imageView: ImageView, source: String?, circle: Boolean = false) {
    Glide.with(imageView).load(source).also {
        if (circle) {
            it.apply(RequestOptions.circleCropTransform())
        }
    }.into(imageView)
}