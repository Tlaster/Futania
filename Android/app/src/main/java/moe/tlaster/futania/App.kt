package moe.tlaster.futania

import android.app.Application
import android.content.Context
import com.github.kittinunf.fuel.core.FuelManager
import moe.tlaster.futania.common.AndroidLogRequestInterceptor
import moe.tlaster.futania.common.AndroidLogResponseInterceptor
import moe.tlaster.futania.common.CookieRequestInterceptor


lateinit var appContext: Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        FuelManager.instance.apply {
            if (BuildConfig.DEBUG) {
                addRequestInterceptor(AndroidLogRequestInterceptor)
                addResponseInterceptor(AndroidLogResponseInterceptor)
            }
            addRequestInterceptor(CookieRequestInterceptor)
        }
    }
}
