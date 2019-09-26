package moe.tlaster.futania.api.model

enum class SiteType {
    general,
    //女性向
    princess
}

enum class BannerType {
    primary,
    secondary
}

enum class UserType {
    creator,
    fan,
    visitor
}

enum class RankingType {
    point,
    fan,
    post_like
}

enum class PeriodType {
    daily,
    weekly,
    monthly,
    all
}

enum class OrderType {
    newer,
    updater,
    popular
}

enum class R18Type {
    both,
    non,
    only
}

enum class ProductType {
    all,
    transfer_by_self,
    download
}