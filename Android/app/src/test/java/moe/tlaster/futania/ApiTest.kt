package moe.tlaster.futania

import kotlinx.coroutines.runBlocking
import moe.tlaster.futania.api.Api
import moe.tlaster.futania.api.model.PeriodType
import moe.tlaster.futania.api.model.RankingType
import moe.tlaster.futania.api.model.SiteType
import org.junit.Test

class ApiTest {
    @Test
    fun news() = runBlocking {
        val news = Api.news()
        assert(news.news.any())
    }

    @Test
    fun banner() = runBlocking {
        val banner = Api.banners()
        assert(banner.bnrs.any())
    }

    @Test
    fun category() = runBlocking {
        val category = Api.category()
        assert(category.categories.any())
    }

    @Test
    fun ranking() = runBlocking {
        val ranking = Api.ranking(
            category = "all",
            gender_type = SiteType.general,
            kind = RankingType.fan,
            page = 1,
            per_page = 10,
            period = PeriodType.daily
        )
        assert(ranking.fanclubs.any())
    }
}