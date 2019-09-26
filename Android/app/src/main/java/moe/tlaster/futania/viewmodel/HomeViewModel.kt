package moe.tlaster.futania.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList
import moe.tlaster.futania.api.Api
import moe.tlaster.futania.api.model.*

data class SimpleModel(
    val text: String
)

class HomeViewModel : ViewModelBase() {

    private var currentUser: CurrentUser? = null
    private var category: List<Category> = listOf()
    private var userType: UserType = UserType.visitor
    val posts: ObservableArrayList<Post> = ObservableArrayList()
    val banner = ObservableArrayList<BannerData>()
    val news = ObservableArrayList<NewsData>()
    val items by lazy {
        ObservableArrayList<SimpleModel>().apply {
            addAll((0 until 3).map { SimpleModel(it.toString()) })
        }
    }

    suspend fun refresh() {
        posts.clear()
        news.clear()
        banner.clear()
        //TODO: get user type
        Api.banners().bnrs[userType]?.let { banner.addAll(it) }
        Api.news().news[userType]?.let { news.addAll(it) }
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