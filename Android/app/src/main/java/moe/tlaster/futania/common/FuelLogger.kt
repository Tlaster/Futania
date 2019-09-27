package moe.tlaster.futania.common

import android.util.Log
import com.github.kittinunf.fuel.core.FoldableRequestInterceptor
import com.github.kittinunf.fuel.core.FoldableResponseInterceptor
import com.github.kittinunf.fuel.core.RequestTransformer
import com.github.kittinunf.fuel.core.ResponseTransformer


object AndroidLogRequestInterceptor : FoldableRequestInterceptor {
    override fun invoke(next: RequestTransformer): RequestTransformer {
        return { request ->
            Log.i("Furl Request", request.toString())
            next(request)
        }
    }
}


object AndroidLogResponseInterceptor : FoldableResponseInterceptor {
    override fun invoke(next: ResponseTransformer): ResponseTransformer {
        return { request, response ->
            Log.i("Furl Response", response.toString())
            next(request, response)
        }
    }
}
