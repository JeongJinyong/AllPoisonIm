package apiteam.allpoisonim.api

import apiteam.allpoisonim.api.data.BookStore
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

class BookStoreService {

    interface BookStoreApi {
        @POST("book-store")
        fun getStoreList(@Header("Authorization") token: String): Observable<List<BookStore.Data>>

        @GET("book-store/{id}")
        fun getStoreDetail(@Header("Authorization") token: String,
                           @Path("id") id: Int): Observable<BookStore.Data>
    }

    companion object {
        fun getStoreList(token: String): Observable<List<BookStore.Data>> {
            return ApiFactory.create(BookStoreApi::class.java).getStoreList(token)
        }

        fun getStoreDetail(token: String, id: Int): Observable<BookStore.Data> {
            return ApiFactory.create(BookStoreApi::class.java).getStoreDetail(token, id)
        }
    }
}