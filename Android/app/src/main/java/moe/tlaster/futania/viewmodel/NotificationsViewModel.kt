package moe.tlaster.futania.viewmodel

import androidx.lifecycle.ViewModel
import moe.tlaster.futania.common.collection.IIncrementalSource
import moe.tlaster.futania.common.collection.IncrementalLoadingCollection


data class SimpleModel2(
    val title: String,
    val text: String
)

class SimpleDataSource2 : IIncrementalSource<SimpleModel2> {
    override suspend fun getPagedItemAsync(page: Int, count: Int): List<SimpleModel2> {
        return (0 until 100).map { SimpleModel2("title ${it + (page * 100)}", "text ${it + (page * 100)}") }
    }

}


class NotificationsViewModel : ViewModel() {

    val items by lazy {
        IncrementalLoadingCollection(SimpleDataSource2())
    }
}