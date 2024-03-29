package moe.tlaster.futania.viewmodel

import androidx.lifecycle.ViewModel
import moe.tlaster.futania.api.Api
import moe.tlaster.futania.api.model.MeResponse
import moe.tlaster.futania.common.async
import moe.tlaster.futania.common.fireAndForgot

class MeViewModel : ViewModel() {

    var profile: MeResponse? = null

    val refresh = async {
        kotlin.runCatching {
            Api.me()
        }.onSuccess {
            profile = it
        }.onFailure {
        }
    }

    init {
        refresh.fireAndForgot()
    }

}