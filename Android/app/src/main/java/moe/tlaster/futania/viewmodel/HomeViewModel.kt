package moe.tlaster.futania.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList

data class SimpleModel(
    val text: String
)

class HomeViewModel : ViewModelBase() {


    val items by lazy {
        ObservableArrayList<SimpleModel>().apply {
            addAll((0 until 100).map { SimpleModel(it.toString()) })
        }
    }

    val itemClicked: (SimpleModel) -> Unit = {
        Log.i("Futania", "OnItemClicked: ${it.text}")
    }
}