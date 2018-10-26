package apiteam.allpoisonim.api

import apiteam.allpoisonim.api.data.BookDetail
import apiteam.allpoisonim.api.data.BookStore
import apiteam.allpoisonim.api.data.Membership
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.IOException

interface HttpRequest {

    companion object {
        fun create(): HttpRequest {
            val httpLogging = HttpLoggingInterceptor()
            httpLogging.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient().newBuilder().apply {
                addInterceptor(HeaderSettingInterceptor())
                addInterceptor(httpLogging)

            }.build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://13.209.168.105:7777")
                .client(okHttpClient)
                .build()

            return retrofit.create(HttpRequest::class.java)
        }
    }

    private class HeaderSettingInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {

            val chainRequest = chain.request()

            val request = chainRequest.newBuilder().apply {
                header("Accept", "application/json")
                header("Content-Type", "application/json")
            }.build()

            return chain.proceed(request)
        }
    }


    @POST("/user/sign-up")
    fun signUp(@Body map: Map<String, String>): Single<retrofit2.Response<Membership.Sign>>

    @POST("/user/sign-in")
    fun signIn(@Body map: Map<String, String>): Single<retrofit2.Response<Membership.Sign>>

    @GET("/book-store/{id}")
    fun bookStore(@Path("od") id: String): Single<BookStore.Data>

    @GET("/book/{bookId}")
    fun book(@Header("Authorization") token: String, @Path("bookId") id:String): Single<retrofit2.Response<BookDetail.Book>>

    @POST("/bookLike/save")
    fun bookLike(@Header("Authorization") token: String, @Body map: Map<String, String>) : Single<retrofit2.Response<BookDetail.BookLike>>

    @POST("/book-comment/save")
    fun bookComment(@Header("Authorization") token: String, @Body map: Map<String, String>) : Single<retrofit2.Response<Membership.Sign>>
}