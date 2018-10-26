package apiteam.allpoisonim.api

import apiteam.allpoisonim.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import okhttp3.logging.HttpLoggingInterceptor

const val TOKEN = "eyJ0eXAiOiJKV1QiLCJjcmVhdGVkQXQiOjE1NDA1Mjc0MDc2MDIsImFsZyI6IkhTMjU2In0.eyJzdWIiOiJ1c2VyIiwidXNlciI6eyJ1c2VySWQiOjMsIm5pY2tuYW1lIjoi66eJ6rO166qsIiwiZW1haWwiOiJkYmd1c3RscjkyQGdtYWlsLmNvbSIsImNyZWF0ZWRBdCI6bnVsbCwidXBkYXRlZEF0IjpudWxsfX0.LqjzcFlaokWwBQ_HJZxBtXuPzUeFHhoMgEBWcOK3n88"

object ApiFactory {
    private val API_BASE_URL = "http://13.209.168.105:7777/"

    private fun defaultRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build()
    }

    fun <T> create(service: Class<T>): T {
        return defaultRetrofit().create(service)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor { chain -> createRequest(chain) }
                .build()
    }

    @Throws(IOException::class)
    private fun createRequest(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.removeHeader("Content-Type")
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("Accept", "application/json")

        return chain.proceed(requestBuilder.build())
    }

}
