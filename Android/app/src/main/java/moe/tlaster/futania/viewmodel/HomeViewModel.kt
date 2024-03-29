package moe.tlaster.futania.viewmodel

import moe.tlaster.futania.api.Api
import moe.tlaster.futania.api.model.*
import moe.tlaster.futania.common.async
import moe.tlaster.futania.common.collection.ObservableCollection
import moe.tlaster.futania.common.fireAndForgot

class HomeViewModel : ViewModelBase() {
    private var currentUser: CurrentUser? = null
    private var category: List<Category> = listOf()
    private var userType: UserType = UserType.visitor
    val posts = ObservableCollection<Post>()
    val banner = ObservableCollection<BannerData>()
    val news = ObservableCollection<NewsData>()

    val refresh = async {
        posts.clear()
        news.clear()
        banner.clear()
        //TODO: get user type
        Api.banners().bnrs[userType]?.let { banner.addAll(it) }
        Api.news().news[userType]?.let {
            news.addAll(it)
        }
        category = Api.category().categories
        kotlin.runCatching {
            Api.me()
        }.onFailure {
            posts.addAll(Api.Search.posts(R18Type.non, category.first().slug, OrderType.newer, page = 1 ,per_page = 12, siteType = SiteType.general).posts)
        }.onSuccess {
            posts.addAll(Api.Timeline.posts(1, per = 12).posts)
            currentUser = it.currentUser
        }
    }

    init {
        refresh.fireAndForgot()
    }
}

