package moe.tlaster.futania.common.bindingAdapter

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

@BindingAdapter("source")
fun source(webView: WebView, url: String) {
    webView.settings.javaScriptEnabled = true
    webView.loadUrl(url)
}

private val BINDINGWEBVIEWCLIENT_ID = -811

@BindingAdapter("onPageFinished")
fun onPageFinished(webView: WebView, callback: (url: String?) -> Unit) {
    if (webView.getTag(BINDINGWEBVIEWCLIENT_ID) !is BindingWebViewClient) {
        BindingWebViewClient().let {
            webView.webViewClient = it
            webView.setTag(BINDINGWEBVIEWCLIENT_ID, it)
        }
    }
    webView.getTag(BINDINGWEBVIEWCLIENT_ID)?.let {
        it as? BindingWebViewClient
    }?.let {
        it.pageFinished = callback
    }
}


class BindingWebViewClient : WebViewClient() {

    var pageFinished: ((url: String?) -> Unit)? = null

    override fun onPageFinished(view: WebView?, url: String?) {
        pageFinished?.invoke(url)
    }
}