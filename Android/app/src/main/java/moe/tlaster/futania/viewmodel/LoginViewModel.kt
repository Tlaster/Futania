package moe.tlaster.futania.viewmodel

import android.webkit.CookieManager
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moe.tlaster.futania.api.Api
import moe.tlaster.futania.common.Settings

class LoginViewModel : ViewModelBase() {

    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    val onPageStarted = { url: String? ->
        url?.let {
            if (it.startsWith("https://fantia.jp")) {
                CookieManager.getInstance().getCookie("https://fantia.jp/")
                    .split(';')
                    .map {
                        val res = it.split('=')
                        res[0] to res[1]
                    }
                    .toMap()
                    .let { cookie ->
                        val key = "_session_id"
                        if (cookie.containsKey(key)) {
                            cookie[key]?.let { session_id ->
                                Settings.set("SessionID", session_id)
                                GlobalScope.launch {
                                    kotlin.runCatching {
                                        Api.me()
                                    }.onSuccess {
                                        loginSuccess.value = true
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }
}