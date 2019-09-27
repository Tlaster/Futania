package moe.tlaster.futania.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import moe.tlaster.futania.api.Api
import moe.tlaster.futania.api.model.Fanclub
import moe.tlaster.futania.api.model.PeriodType
import moe.tlaster.futania.api.model.Post
import moe.tlaster.futania.api.model.RankingType
import moe.tlaster.futania.common.async
import moe.tlaster.futania.common.collection.ObservableCollection

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
}