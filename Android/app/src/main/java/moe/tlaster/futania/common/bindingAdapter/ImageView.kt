package moe.tlaster.futania.common.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("source")
fun source(imageView: ImageView, source: String) {
    Glide.with(imageView).load(source).into(imageView)
}