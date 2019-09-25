package moe.tlaster.futania.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList
import moe.tlaster.futania.api.Api
import moe.tlaster.futania.api.model.*

data class SimpleModel(
    val text: String
)

class HomeViewModel : ViewModelBase() {

    private var news: NewsResponse? = null
    private var banner: BannerResponse? = null
    private var currentUser: CurrentUser? = null
    var posts: ObservableArrayList<Post> = ObservableArrayList()
    private var category: List<Category> = listOf()
    val items by lazy {
        ObservableArrayList<SimpleModel>().apply {
            addAll((0 until 100).map { SimpleModel(it.toString()) })
        }
    }

    val itemClicked: (SimpleModel) -> Unit = {
        Log.i("Futania", "OnItemClicked: ${it.text}")
    }


    suspend fun refresh() {
        posts.clear()
        banner = Api.banners()
        news = Api.news()
        category = Api.category().categories
        val meResponse = Api.me()
        if (meResponse.currentUser != null) {
            posts.addAll(Api.Timeline.posts(1, per = 12).posts)
            currentUser = meResponse.currentUser
        } else {
            posts.addAll(Api.Search.posts(R18Type.non, category.first().slug, OrderType.newer, page = 1 ,per_page = 12, siteType = SiteType.general).posts)
        }
    }
}