package apiteam.allpoisonim.api

import apiteam.allpoisonim.api.data.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HttpRequest {

    companion object {
        fun create(): HttpRequest {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://en.wikipedia.org/w/")
                    .build()

            return retrofit.create(HttpRequest::class.java)
        }
    }

    @POST("/user/sign-up")
    @FormUrlEncoded
    fun signUp(@FieldMap map:Map<String, String>) : Single<User.Sign>

    @POST("/user/sign-in")
    @FormUrlEncoded
    fun signIn(@FieldMap map:Map<String, String>) : Single<User.Sign>
}