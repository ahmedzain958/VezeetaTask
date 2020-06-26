package com.vezeeta.vezeetatask.data.source.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeadersInterceptor :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(setupRequestHeaders(chain.request()))
    }

    private fun setupRequestHeaders(oldRequest: Request): Request {
        return oldRequest.newBuilder()
            .addHeader("Access-Control-Allow-Origin", "*")
            .addHeader("Content-Encoding", "gzip")
            .addHeader("Content-Type", "application/json; charset=UTF-8")
            .addHeader("Server", "nginx")
            .addHeader("Vary", "Accept-Encoding")
            .addHeader("X-RateLimit-Limit", "120")
            .addHeader("X-RateLimit-Remaining", "116")
            .addHeader("X-RateLimit-Reset", "1593150731")
            .build()
    }

}
