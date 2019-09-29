package moe.tlaster.futania.viewmodel

import androidx.lifecycle.ViewModel
import moe.tlaster.futania.api.Api
import moe.tlaster.futania.api.model.Fanclub
import moe.tlaster.futania.api.model.PeriodType
import moe.tlaster.futania.api.model.Post
import moe.tlaster.futania.api.model.RankingType
import moe.tlaster.futania.common.async
import moe.tlaster.futania.common.collection.ObservableCollection
import moe.tlaster.futania.common.fireAndForgot

class SearchViewModel : ViewModel() {
    val postsRanking = ObservableCollection<Post>()
    val fanclubsRanking = ObservableCollection<Fanclub>()
    
    val refresh = async {
        postsRanking.clear()
        fanclubsRanking.clear()
        val category = Api.category().categories
        Api.Ranking.posts(category = category.first().slug, period = PeriodType.daily, kind = RankingType.point).posts.let {
            postsRanking.addAll(it)
        }
        Api.Ranking.fanclubs(category = category.first().slug, period = PeriodType.daily, kind = RankingType.point).fanclubs.let {
            fanclubsRanking.addAll(it)
        }
    }

    init {
        refresh.fireAndForgot()
    }
}