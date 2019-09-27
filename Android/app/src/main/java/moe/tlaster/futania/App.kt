package moe.tlaster.futania

import android.app.Application
import android.os.Build
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.*
import moe.tlaster.futania.common.AndroidLogRequestInterceptor
import moe.tlaster.futania.common.AndroidLogResponseInterceptor

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FuelManager.instance.apply {
            if (BuildConfig.DEBUG) {
                addRequestInterceptor(AndroidLogRequestInterceptor)
                addResponseInterceptor(AndroidLogResponseInterceptor)
            }
        }
    }
}
